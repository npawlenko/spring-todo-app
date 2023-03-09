package com.github.npawlenko.todoapp.todoapp.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super(
                String.format("No such user with given ID: %d", userId)
        );
    }
}
