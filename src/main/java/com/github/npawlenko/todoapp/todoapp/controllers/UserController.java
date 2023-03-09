package com.github.npawlenko.todoapp.todoapp.controllers;

import com.github.npawlenko.todoapp.todoapp.models.TodoTopic;
import com.github.npawlenko.todoapp.todoapp.models.User;
import com.github.npawlenko.todoapp.todoapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(consumes = "application/json")
    public void createUser(@RequestBody User user) {
        service.saveUser(user);
    }

    @PutMapping(value = "{userId}", consumes = "application/json")
    public void updateUser(@PathVariable(name = "userId") Long userId, @RequestBody User user) {
        service.updateUser(user);
    }

    @DeleteMapping("{userId}")
    public void deleteUser(@PathVariable(name = "userId") Long userId) {
        service.deleteUser(userId);
    }

    @GetMapping("{userId}")
    public User getUserById(@PathVariable("userId") Long userId) {
        return service.getUserById(userId);
    }



    @GetMapping("{userId}/topics")
    public List<TodoTopic> getTodoTopics(@PathVariable(name = "userId") Long userId) {
        return service.getTodoTopicsByUserId(userId);
    }
}
