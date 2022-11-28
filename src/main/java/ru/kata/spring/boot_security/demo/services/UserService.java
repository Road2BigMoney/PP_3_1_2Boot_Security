package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    User loadUserByUsername(String username);
    void updateUser(User user, long id);
    User getUserById(Long id);
    void addUser(User user);
    void deleteUser(Long id);
    List<User> getAllUsers();



}
