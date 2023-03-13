package com.github.npawlenko.todoapp.todoapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class TodoTopic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotBlank(message = "User ID is mandatory")
    @JsonIgnore
    private User user;

    @NotBlank(message = "Title is mandatory")
    private String title;
    private String description;
}
