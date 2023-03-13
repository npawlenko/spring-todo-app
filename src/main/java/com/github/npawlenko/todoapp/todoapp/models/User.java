package com.github.npawlenko.todoapp.todoapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Login is mandatory")
    private String username;

    @NotEmpty
    private char[] password;

    @JsonIgnore
    public char[] getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(char[] password) {
        this.password = password;
    }
}
