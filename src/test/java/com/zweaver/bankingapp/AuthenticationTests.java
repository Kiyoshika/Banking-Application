package com.zweaver.bankingapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zweaver.bankingapp.controller.UserController;
import com.zweaver.bankingapp.entity.UserEntity;
import com.zweaver.bankingapp.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationTests {

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() throws Exception {
        userRepository.deleteAll();

        // every test relies on a user being created
        UserEntity newUser = new UserEntity("jim", "123");
        mockMvc.perform(post("/api/v1/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(newUser)))
                .andExpect(status().isCreated());
    }

    @AfterEach
    public void cleanUp() {
        userRepository.deleteAll();
    }

    @Test
    public void createNewUserThatAlreadyExists() throws Exception {
        UserEntity newUser2 = new UserEntity("jim", "456");
        mockMvc.perform(post("/api/v1/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(newUser2)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void logInSuccessfully() throws Exception {
        UserEntity newUser = new UserEntity("jim", "123");
        mockMvc.perform(post("/api/v1/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(newUser)))
                .andExpect(status().isOk());
    }

    @Test
    public void logInWithWrongCredentials() throws Exception {
        UserEntity newUser = new UserEntity("jim", "456");
        mockMvc.perform(post("/api/v1/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(newUser)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteUserThatAlreadyExists() throws Exception {
        UserEntity newUser = new UserEntity("jim", "123");
        mockMvc.perform(delete("/api/v1/users/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(newUser)))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUserThatDoesNotExist() throws Exception {
        UserEntity newUser = new UserEntity("kyle", "123");
        mockMvc.perform(delete("/api/v1/users/delete")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(newUser)))
                .andExpect(status().isBadRequest());
    }
}
