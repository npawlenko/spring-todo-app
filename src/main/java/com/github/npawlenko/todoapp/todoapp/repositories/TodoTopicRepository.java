package com.github.npawlenko.todoapp.todoapp.repositories;

import com.github.npawlenko.todoapp.todoapp.models.TodoItem;
import com.github.npawlenko.todoapp.todoapp.models.TodoTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoTopicRepository extends JpaRepository<TodoTopic, Long> {
    @Query("SELECT ti FROM TodoItem ti WHERE ti.topic.id = ?1")
    List<TodoItem> getTodoItemsByTodoTopicId(Long todoTopicId);
}
