package com.github.npawlenko.todoapp.todoapp.repositories;

import com.github.npawlenko.todoapp.todoapp.models.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
}
