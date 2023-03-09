package com.github.npawlenko.todoapp.todoapp.repositories;


import com.github.npawlenko.todoapp.todoapp.model.TodoTopic;
import com.github.npawlenko.todoapp.todoapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT tt FROM TodoTopic tt WHERE tt.user.id = ?1")
    List<TodoTopic> getTodoTopicsByUserId(Long userId);
}
