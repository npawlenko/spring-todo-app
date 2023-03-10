package com.github.npawlenko.todoapp.todoapp.controllers;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.npawlenko.todoapp.todoapp.models.User;
import com.github.npawlenko.todoapp.todoapp.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    UserRepository userRepository;

    @Test
    void getUsers() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/users")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Transactional
    @Test
    void createUser() throws Exception {
        userRepository.deleteById(1L);
        userRepository.deleteUserByLogin("test");
        User user = new User(1L, "test", "password".toCharArray());

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders
                        .post("/api/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());

        userRepository.deleteById(1L);
    }

    @Transactional
    @Test
    void updateUser() throws Exception {
        userRepository.deleteUserByLogin("updateTest");
        User user = new User(1L, "test", "password".toCharArray());
        User savedUser = userRepository.save(user);
        savedUser.setLogin("updateTest");

        mvc.perform(MockMvcRequestBuilders
                        .put("/api/users/{userId}", savedUser.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(savedUser))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        userRepository.deleteById(savedUser.getId());
    }

    @Test
    void deleteUser() throws Exception {
        User user = new User(0L, "test", "password".toCharArray());
        User savedUser = userRepository.save(user);

        mvc.perform(MockMvcRequestBuilders
                        .delete("/api/users/{userId}", savedUser.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        userRepository.delete(savedUser);
    }

    @Test
    void getUserById() throws Exception {
        User user = new User(0L, "test", "password".toCharArray());
        User savedUser = userRepository.save(user);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/users/{userId}", savedUser.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isMap());

        userRepository.delete(savedUser);
    }

    @Test
    void getTodoTopics() throws Exception {
        User user = new User(0L, "test", "password".toCharArray());
        User savedUser = userRepository.save(user);

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/users/1/topics")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());

        userRepository.delete(savedUser);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper()
                    .setAnnotationIntrospector(AnnotationIntrospector.nopInstance())
                    .writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}