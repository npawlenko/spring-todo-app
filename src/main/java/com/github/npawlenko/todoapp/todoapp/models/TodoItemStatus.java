package com.github.npawlenko.todoapp.todoapp.models;

public enum TodoItemStatus {
    INCOMPLETE("INCOMPLETE"),
    COMPLETE("COMPLETE");

    private String status;

    TodoItemStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString(){
        return status;
    }
}
