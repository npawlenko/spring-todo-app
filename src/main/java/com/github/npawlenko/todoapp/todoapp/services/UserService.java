package com.github.npawlenko.todoapp.todoapp.services;

import com.github.npawlenko.todoapp.todoapp.exceptions.UserNotFoundException;
import com.github.npawlenko.todoapp.todoapp.models.TodoTopic;
import com.github.npawlenko.todoapp.todoapp.models.User;
import com.github.npawlenko.todoapp.todoapp.repositories.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
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
        boolean existsLogin = repository.existsByLogin(user.getLogin());
        if (existsLogin) {
            throw new EntityExistsException("User with given login already exists");
        }
        return repository.save(user);
    }

    public User updateUser(User user) {
        boolean exists = repository.existsById(user.getId());
        if (!exists) {
            throw new EntityNotFoundException("User with given id does not exist");
        }
        Optional<User> optionalUser = repository.getUserByLogin(user.getLogin());
        if (optionalUser.isPresent()) {
            User foundUserWithLogin = optionalUser.get();
            if (!foundUserWithLogin.getId().equals(user.getId())) {
                throw new EntityExistsException("User with given login already exists!");
            }
        }
        return repository.save(user);
    }

    public void deleteUser(Long userId) {
        boolean exists = repository.existsById(userId);
        if (!exists) {
            throw new EntityNotFoundException("User with given id does not exist");
        }
        repository.deleteById(userId);
    }

    public User getUserById(Long userId) throws UserNotFoundException {
        Optional<User> userOptional = repository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new EntityNotFoundException("User with given id does not exist");
        }
        return userOptional.get();
    }

    public List<TodoTopic> getTodoTopicsByUserId(Long userId) {
        return repository.getTodoTopicsByUserId(userId);
    }
}
