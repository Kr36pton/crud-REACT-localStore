package com.sofka.crud.services;

import com.sofka.crud.models.Todo;
import com.sofka.crud.repositories.TodoRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TodoServiceTest {
    @Autowired
    TodoRepository todoRepository;

    @Test
    public void testGuardarTodo() {

        Todo todoModel = new Todo(2L, "Terminar el codigo", false);
        Todo todoModelRegistrado = todoRepository.save(todoModel);
        assertNotNull(todoModelRegistrado);
    }

    @Test
    public void testListarTodos() {
        Todo todoModel = new Todo(1L, "Comprar comida", false);
        Todo todoModelRegistrado = todoRepository.save(todoModel);
        List<Todo> todoModelList = (List<Todo>) todoRepository.findAll();
        assertThat(todoModelList).size().isGreaterThan(0);
    }

    //Mock
    @Mock
    private Todo todoMock;

    @Before
    public void setupMock() {
        todoMock = mock(Todo.class);
    }

    @Test
    public void testTextTodo() {
        when(todoMock.getText()).thenReturn("Comprar comida");
        assertEquals("Comprar comida", todoMock.getText());
    }

    @Test
    public void testCompletedTodo() {
        when(todoMock.getCompleted()).thenReturn(false);
        assertEquals(false, todoMock.getCompleted());
    }
}
