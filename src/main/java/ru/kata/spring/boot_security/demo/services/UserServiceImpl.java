package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserDAO;


import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Override
    public void updateUser(User user, long id) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userDAO.update(user);
    }
    @Override
    public User getUserById(Long id) {
        return userDAO.getById(id);
    }
    @Override
    public void addUser(User user) {
        if (user != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDAO.save(user);
        }
    }
    @Override
    public void deleteUser(Long id) {
        userDAO.deleteById(id);
    }
    @Override
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }


}
