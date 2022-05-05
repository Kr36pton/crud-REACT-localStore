package com.sofka.crud.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sofka.crud.models.Todo;
import com.sofka.crud.services.TodoService;

@CrossOrigin
@RestController
@RequestMapping("/todos")
public class TodoControllers {

    @Autowired
    TodoService todoService;

    @GetMapping()
    public ArrayList<Todo> obtenerTodos() {
        return todoService.obtenerTodos();
    }

    @PostMapping(path = "/add")
    public Todo guardarTodos(@RequestBody Todo todo) {
        return this.todoService.guardarTodos(todo);
    }

    @GetMapping(path = "/{id}")
    public Optional<Todo> obtenerTodoPorId(@PathVariable("id") Long id) {
        return this.todoService.obtenerPorId(id);
    }

    @PutMapping(path = "/updatetodo/{id}")
    Todo replaceTodo(@RequestBody Todo newtodo, @PathVariable Long id) {

        return todoService.obtenerPorId(id)
                .map(todo -> {
                    todo.setText(newtodo.getText());
                    todo.setCompleted(newtodo.getCompleted());
                    return guardarTodos(todo);
                })
                .orElseGet(() -> extracted(newtodo, id));
    }

    private Todo extracted(Todo newtodo, Long id) {
        newtodo.setId(id);
        return guardarTodos(newtodo);
    }

    @DeleteMapping(path = "eliminar-todos/{id}")
    public String eliminarPorId(@PathVariable("id") Long id) {
        boolean ok = this.todoService.eliminarTodo(id);
        if (ok) {
            return "Se eliminó el To Do con id " + id;
        } else {
            return "No pudo eliminar el To Do con id" + id;
        }
    }
}