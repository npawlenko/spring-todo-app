package com.github.npawlenko.todoapp.todoapp.repositories;


import com.github.npawlenko.todoapp.todoapp.models.TodoTopic;
import com.github.npawlenko.todoapp.todoapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByLogin(String login);
    Optional<User> getUserByLogin(String login);

    void deleteUserByLogin(String login);

    @Query("SELECT tt FROM TodoTopic tt WHERE tt.user.id = ?1")
    List<TodoTopic> getTodoTopicsByUserId(Long userId);
}
