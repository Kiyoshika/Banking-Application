package com.zweaver.bankingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthenticationController {
    @RequestMapping("/login")
    public String login(Model model) {
        return "login.html";
    }
}
