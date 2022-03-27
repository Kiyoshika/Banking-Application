package com.zweaver.bankingapp.controller;

import com.zweaver.bankingapp.entity.UserEntity;
import com.zweaver.bankingapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/users/")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("register")
    @ResponseBody
    public ResponseEntity createUser(@RequestBody UserEntity userEntity) {
        try {
            userService.createNewUser(userEntity.getUsername(), userEntity.getPassword());
            return new ResponseEntity(HttpStatus.CREATED);
        } catch(Exception ex) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("login")
    @ResponseBody
    public ResponseEntity loginAsUser(@RequestBody UserEntity userEntity) {
        if (userService.loginAsUser(userEntity.getUsername(), userEntity.getPassword()))
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("delete")
    @ResponseBody
    public ResponseEntity deleteUser(@RequestBody UserEntity userEntity) {
        if (userService.loginAsUser(userEntity.getUsername(), userEntity.getPassword())) {
            userService.dropUserByUsername(userEntity.getUsername());
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
