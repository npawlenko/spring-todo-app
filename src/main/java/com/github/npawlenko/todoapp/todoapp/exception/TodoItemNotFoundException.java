package com.github.npawlenko.todoapp.todoapp.exception;

public class TodoItemNotFoundException extends RuntimeException {
    public TodoItemNotFoundException(Long todoItemId) {
        super(
                String.format("No such todo item with given ID: %d", todoItemId)
        );
    }
}
