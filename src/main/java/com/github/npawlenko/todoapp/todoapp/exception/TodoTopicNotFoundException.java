package com.github.npawlenko.todoapp.todoapp.exception;

public class TodoTopicNotFoundException extends RuntimeException {
    public TodoTopicNotFoundException(Long todoTopicId) {
        super(
                String.format("No such todo topic with given ID: %d", todoTopicId)
        );
    }
}
