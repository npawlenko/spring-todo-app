package com.github.npawlenko.todoapp.todoapp.services;

import com.github.npawlenko.todoapp.todoapp.exceptions.UserAlreadyExistsException;
import com.github.npawlenko.todoapp.todoapp.exceptions.UserNotFoundException;
import com.github.npawlenko.todoapp.todoapp.models.TodoTopic;
import com.github.npawlenko.todoapp.todoapp.models.User;
import com.github.npawlenko.todoapp.todoapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    public User saveUser(User user) {
        Optional<User> userOptional = repository.findById(user.getId());
        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException(user.getId());
        }
        return repository.save(user);
    }

    public User updateUser(User user) {
        Optional<User> userOptional = repository.findById(user.getId());
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(user.getId());
        }
        return repository.save(user);
    }

    public void deleteUser(Long userId) {
        boolean exists = repository.existsById(userId);
        if (!exists) {
            throw new UserNotFoundException(userId);
        }
        repository.deleteById(userId);
    }

    public User getUserById(Long userId) throws UserNotFoundException {
        Optional<User> userOptional = repository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException(userId);
        }
        return userOptional.get();
    }

    public List<TodoTopic> getTodoTopicsByUserId(Long userId) {
        return repository.getTodoTopicsByUserId(userId);
    }
}
