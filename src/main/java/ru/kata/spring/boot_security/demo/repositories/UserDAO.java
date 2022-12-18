package ru.kata.spring.boot_security.demo.repositories;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;


public interface UserDAO  {

    User findByUsername(String username);
    User getById(long id);

    List<User> findAll();

    void deleteById(long id);

    void save(User user);
    void update(User user);
}
