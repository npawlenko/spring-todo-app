package com.github.npawlenko.todoapp.todoapp.controllers;

import com.github.npawlenko.todoapp.todoapp.model.TodoTopic;
import com.github.npawlenko.todoapp.todoapp.model.User;
import com.github.npawlenko.todoapp.todoapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getUsers() {
        return service.getUsers();
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable("id") Long userId) {
        return service.getUserById(userId);
    }

    @GetMapping("{id}/topics")
    public List<TodoTopic> getTodoTopics(@PathVariable(name = "id") Long userId) {
        return service.getTodoTopicsByUserId(userId);
    }
}
