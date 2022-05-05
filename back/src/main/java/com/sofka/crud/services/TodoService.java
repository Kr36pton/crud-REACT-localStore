package com.sofka.crud.services;

import org.springframework.stereotype.Service;
import com.sofka.crud.models.Todo;
import com.sofka.crud.repositories.TodoRepository;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TodoService {

    @Autowired
    TodoRepository todoRepository;

    public ArrayList<Todo> obtenerTodos(){
        return (ArrayList<Todo>) todoRepository.findAll();
    }

    public Todo guardarTodos(Todo todo){
        return todoRepository.save(todo);
    }

    public Optional<Todo> obtenerPorId(Long id){
        return todoRepository.findById(id);
    }

    public boolean eliminarTodo(Long id) {
        try{
            todoRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }
}