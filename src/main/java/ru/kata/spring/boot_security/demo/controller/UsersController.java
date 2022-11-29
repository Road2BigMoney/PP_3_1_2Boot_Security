package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/")
    public String index(Model model){

        return "/login";
    }
    @GetMapping("/login")
    public String loginPage(Model model){

        return "/login";
    }

    @GetMapping("/user")
    public String userInfo(Principal principal, Model model) {
        model.addAttribute("userCurrent", userService.loadUserByUsername(principal.getName()));
        return "userPage";
    }



}
