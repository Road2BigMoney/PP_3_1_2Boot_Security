package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.security.Principal;
import java.sql.SQLException;

@Controller

public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserServiceImpl userService) {
        this.userService = userService;
    }



    @GetMapping("user/")
    public String userInfo(Principal principal, Model model) {
        model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        return "user-info";
    }



}
