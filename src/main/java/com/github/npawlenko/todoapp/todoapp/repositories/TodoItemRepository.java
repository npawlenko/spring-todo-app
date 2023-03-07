package com.github.npawlenko.todoapp.todoapp.repositories;

import com.github.npawlenko.todoapp.todoapp.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
    @Query("SELECT ti FROM TodoItem ti WHERE ti.topic.id = ?1")
    List<TodoItem> findByTodoTopicId(Long todoTopicId);
}
