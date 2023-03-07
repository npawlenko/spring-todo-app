package com.github.npawlenko.todoapp.todoapp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Data
@Entity
@Table
public class TodoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String description;
    private TodoItemStatus status;
    private LocalDate date;
}
