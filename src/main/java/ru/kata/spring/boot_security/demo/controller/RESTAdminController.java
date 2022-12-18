package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;

import java.util.List;


@RestController
@RequestMapping("/api")
public class RESTAdminController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public RESTAdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>( userService.getAllUsers(), HttpStatus.OK);
    }
    @GetMapping("/user")
    public ResponseEntity<User> getCurrent(Principal principal) {
        return new ResponseEntity<>(userService.loadUserByUsername(principal.getName()), HttpStatus.OK);
    }
    @GetMapping("user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<User> create(@RequestBody User user) {

        userService.addUser(user, user.getRoles());
        return new ResponseEntity<>(userService.getUserById(user.getId()),HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestBody User user) {

        userService.updateUser(user, user.getId(), user.getRoles());
        User updatedUser = userService.getUserById(user.getId());
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    @GetMapping("/header")
    public ResponseEntity<User> getAuthentication(Principal principal) {
        User user = userService.loadUserByUsername(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles() {

        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
