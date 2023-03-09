package com.github.npawlenko.todoapp.todoapp.services;

import com.github.npawlenko.todoapp.todoapp.exceptions.TodoTopicNotFoundException;
import com.github.npawlenko.todoapp.todoapp.models.TodoItem;
import com.github.npawlenko.todoapp.todoapp.models.TodoTopic;
import com.github.npawlenko.todoapp.todoapp.repositories.TodoTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoTopicService {

    private final TodoTopicRepository repository;

    @Autowired
    public TodoTopicService(TodoTopicRepository repository) {
        this.repository = repository;
    }

    public List<TodoTopic> getTodoTopics() {
        return repository.findAll();
    }

    public TodoTopic getTodoTopicById(Long todoTopicId) {
        Optional<TodoTopic> todoTopicOptional = repository.findById(todoTopicId);
        if (todoTopicOptional.isEmpty()) {
            throw new TodoTopicNotFoundException(todoTopicId);
        }
        return todoTopicOptional.get();
    }

    public List<TodoItem> getTodoItemsByTodoTopicId(Long todoTopicId) {
        return repository.getTodoItemsByTodoTopicId(todoTopicId);
    }
}
