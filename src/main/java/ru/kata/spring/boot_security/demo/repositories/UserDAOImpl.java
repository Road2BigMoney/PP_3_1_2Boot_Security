package ru.kata.spring.boot_security.demo.repositories;

import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class UserDAOImpl implements UserDAO{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public User findByUsername(String username) {
        return entityManager.createQuery("select u from User u where u.username = :username", User.class)
                .getResultStream()
                .findFirst()
                .get();

    }

    @Override
    public User getById(long id) {
        return entityManager.createQuery("select u from User u where u.id = :id", User.class)
                .getResultStream()
                .findFirst()
                .get();
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("select u from User ").getResultList();
    }

    @Override
    public void deleteById(long id) {
        entityManager.createQuery("delete from User u where u.id=:id").executeUpdate();
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }
}
