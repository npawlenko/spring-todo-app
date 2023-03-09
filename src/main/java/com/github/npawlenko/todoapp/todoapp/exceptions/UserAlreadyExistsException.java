package com.github.npawlenko.todoapp.todoapp.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(Long userId) {
        super(
                String.format("User with given ID already exists: %d", userId)
        );
    }
}