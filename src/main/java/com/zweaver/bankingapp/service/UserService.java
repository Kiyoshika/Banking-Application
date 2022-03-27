package com.zweaver.bankingapp.service;

import com.zweaver.bankingapp.entity.UserEntity;
import com.zweaver.bankingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void createNewUser(String username, String password) {
        UserEntity newUser = new UserEntity(username, password);
        userRepository.save(newUser);
    }

    @Transactional(readOnly = true)
    public boolean loginAsUser(String username, String password) {
        UserEntity fetchedUser = userRepository.findByUsername(username);
        if (fetchedUser == null) return false;
        return password.equals(fetchedUser.getPassword());
    }

    @Transactional
    public void dropUserByUsername(String username) {
        userRepository.delete(userRepository.findByUsername(username));
    }
}
