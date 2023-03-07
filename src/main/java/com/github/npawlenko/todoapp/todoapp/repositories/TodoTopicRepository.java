package com.github.npawlenko.todoapp.todoapp.repositories;

import com.github.npawlenko.todoapp.todoapp.model.TodoTopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoTopicRepository extends JpaRepository<TodoTopic, Long> {
    @Query("SELECT tt FROM TodoTopic tt WHERE tt.user.id = ?1")
    List<TodoTopic> findByUserId(Long userId);
}
