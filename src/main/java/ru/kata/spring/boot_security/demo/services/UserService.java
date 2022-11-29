package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {
    User loadUserByUsername(String username);

    void updateUser(User user, long id, Set<Role> roles);

    User getUserById(Long id);
    void addUser(User user, Set<Role> roles);
    void deleteUser(Long id);
    List<User> getAllUsers();



}
