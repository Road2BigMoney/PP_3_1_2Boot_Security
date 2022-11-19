package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleDAO;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller

@RequestMapping("/admin")
public class AdminController {
    private UserServiceImpl userService;
    private RoleDAO roleDAO;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserServiceImpl userService, RoleDAO roleDAO, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleDAO = roleDAO;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("users", userService.getAllUsers());
        return "index";
    }
    @GetMapping("user/{id}")
    public String userInfo(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.loadUserByUsername(userService.getUserById(id).getUsername()));
        return "user-info";
    }

    @GetMapping("/new")
    public String saveForm(@ModelAttribute("user") User user) throws SQLException {

        return "save";
    }


    @PostMapping("/new")
    public String saveUser(@ModelAttribute("user") User user) throws SQLException {


        List<Role> roles = new ArrayList<>();
        roles.add(roleDAO.getById(1));
        user.setRoles(roles);
        userService.addUser(user);
        return "redirect:/admin/";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "/edit";
    }

    @PatchMapping("/edit/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") long id){
        List<Role> roles = new ArrayList<>();
        roles.add(roleDAO.getById(1));
        user.setRoles(roles);
        userService.updateUser(user,id);
        return "redirect:/admin/";
    }

    @DeleteMapping("delete/{id}")
    public String delete(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin/";
    }
}
