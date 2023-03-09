package com.github.npawlenko.todoapp.todoapp.exceptions;

import com.github.npawlenko.todoapp.todoapp.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class, TodoItemNotFoundException.class, TodoTopicNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(Exception e, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
