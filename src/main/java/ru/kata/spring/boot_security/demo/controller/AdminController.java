package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleDAO;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;
import java.util.ArrayList;
import java.util.List;

@Controller

@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private RoleDAO roleDAO;


    @Autowired
    public AdminController(UserServiceImpl userService, RoleDAO roleDAO) {
        this.userService = userService;
        this.roleDAO = roleDAO;

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
    public String saveForm(@ModelAttribute("user") User user) {

        return "save";
    }


    @PostMapping("/new")
    public String saveUser(@ModelAttribute("user") User user, @RequestParam("roleId") List<Integer> roleId ) {
        user.setRoles(roleDAO.findMultipleById(roleId));
        userService.addUser(user);
        return "redirect:/admin/";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "/edit";
    }

    @PatchMapping("/edit/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") long id, @RequestParam("roleId") List<Integer> roleId){
        user.setRoles(roleDAO.findMultipleById(roleId));
        userService.updateUser(user,id);
        return "redirect:/admin/";
    }

    @DeleteMapping("delete/{id}")
    public String delete(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin/";
    }
}
