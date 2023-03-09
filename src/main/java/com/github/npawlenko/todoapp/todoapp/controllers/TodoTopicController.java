package com.github.npawlenko.todoapp.todoapp.controllers;

import com.github.npawlenko.todoapp.todoapp.models.TodoItem;
import com.github.npawlenko.todoapp.todoapp.models.TodoTopic;
import com.github.npawlenko.todoapp.todoapp.services.TodoTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/topics")
public class TodoTopicController {

    private final TodoTopicService service;

    @Autowired
    public TodoTopicController(TodoTopicService service) {
        this.service = service;
    }

    @GetMapping
    public List<TodoTopic> getTodoTopics() {
        return service.getTodoTopics();
    }

    @GetMapping("{id}")
    public TodoTopic getTodoTopicById(@PathVariable(name = "id") Long todoTopicId) {
        return service.getTodoTopicById(todoTopicId);
    }

    @GetMapping("{id}/items")
    public List<TodoItem> getTodoItemsByTodoTopicId(Long todoTopicId) {
        return service.getTodoItemsByTodoTopicId(todoTopicId);
    }
}
