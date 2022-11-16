package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserDetailsService;

import java.security.Principal;
import java.sql.SQLException;

@Controller

public class UsersController {

    private final UserDetailsService userService;

    @Autowired
    public UsersController(UserDetailsService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String index(Model model) {

        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }

    @GetMapping("/new")
    public String saveForm(@ModelAttribute("user") User user) throws SQLException {


        return "save";
    }


    @PostMapping("/new")
    public String saveUser(@ModelAttribute("user") User user) throws SQLException {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("user/")
    public String userInfo(Principal principal, Model model) {
        model.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        return "user-info";
    }

    @GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "/edit";
    }

    @PatchMapping("/edit")
    public String update(@ModelAttribute("user") User user){
        userService.updateUser(user);
        return "redirect:/admin/";
    }

    @DeleteMapping("delete/{id}")
    public String delete(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin/";
    }

}
