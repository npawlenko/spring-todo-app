package com.github.npawlenko.todoapp.todoapp.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super(
                String.format("No such user with given ID: %d", userId)
        );
    }
}
