package com.github.npawlenko.todoapp.todoapp.services;

import com.github.npawlenko.todoapp.todoapp.exceptions.TodoItemNotFoundException;
import com.github.npawlenko.todoapp.todoapp.models.TodoItem;
import com.github.npawlenko.todoapp.todoapp.repositories.TodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoItemService {

    private final TodoItemRepository repository;

    @Autowired
    public TodoItemService(TodoItemRepository repository) {
        this.repository = repository;
    }

    public List<TodoItem> getTodoItems() {
        return repository.findAll();
    }

    public TodoItem getTodoItemById(Long todoItemId) {
        Optional<TodoItem> todoItemOptional = repository.findById(todoItemId);
        if (todoItemOptional.isEmpty()) {
            throw new TodoItemNotFoundException(todoItemId);
        }
        return todoItemOptional.get();
    }
}
