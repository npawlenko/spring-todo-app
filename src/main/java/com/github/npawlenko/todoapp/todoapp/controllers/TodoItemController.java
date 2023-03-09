package com.github.npawlenko.todoapp.todoapp.controllers;

import com.github.npawlenko.todoapp.todoapp.model.TodoItem;
import com.github.npawlenko.todoapp.todoapp.services.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/items")
public class TodoItemController {

    private final TodoItemService service;

    @Autowired
    public TodoItemController(TodoItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<TodoItem> getTodoItems() {
        return service.getTodoItems();
    }

    @GetMapping("{id}")
    public TodoItem getTodoItemById(@PathVariable(name = "id") Long todoItemId) {
        return service.getTodoItemById(todoItemId);
    }
}