package com.github.npawlenko.todoapp.todoapp.services;

import com.github.npawlenko.todoapp.todoapp.exception.UserNotFoundException;
import com.github.npawlenko.todoapp.todoapp.model.User;
import com.github.npawlenko.todoapp.todoapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) {
            throw new UserNotFoundException(
                    String.format("Could not find user with given ID (%d)", userId)
            );
        }
        return userOptional.get();
    }
}
