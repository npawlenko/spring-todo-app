package com.github.npawlenko.todoapp.todoapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class TodoTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
}
