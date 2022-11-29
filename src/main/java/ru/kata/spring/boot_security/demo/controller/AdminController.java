package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@Controller

@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private RoleService roleService;


    @Autowired
    public AdminController(UserServiceImpl userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;

    }


    @GetMapping("/")
    public String index(Model model, Principal principal) {
        model.addAttribute("userCurrent", userService.loadUserByUsername(principal.getName()));
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("listRoles", roleService.findAll());
        model.addAttribute("newUser", new User());
        return "adminPanel";
    }


    @PostMapping("/new")
    public String saveUser(@ModelAttribute("newUser") User user, @RequestParam("rolesName") List<String> roles) {
        userService.addUser(user, roleService.getSetRolesByRoleNames(roles));
        return "redirect:/admin/";
    }

    @PatchMapping("/edit/{id}")
    public String update(@ModelAttribute("user") @Valid User user, @RequestParam("rolesName") List<String> roles, @PathVariable("id") long id) {
        userService.updateUser(user, id, roleService.getSetRolesByRoleNames(roles));
        return "redirect:/admin/";
    }

    @DeleteMapping("delete/{id}")
    public String delete(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin/";
    }
}
