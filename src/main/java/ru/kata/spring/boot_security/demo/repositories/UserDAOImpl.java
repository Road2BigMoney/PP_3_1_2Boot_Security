package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findByUsername(String username) {
        User user = entityManager.createQuery("select u from User u join fetch u.roles where u.username = :username", User.class)
                .setParameter("username", username)
                .getResultStream()
                .findFirst()
                .get();
        return user;


    }

    @Override
    public User getById(long id) {
        return entityManager.createQuery("select u from User u where u.id = :id", User.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst()
                .get();
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("select u from User u ").getResultList();
    }

    @Override
    public void deleteById(long id) {
        entityManager.createQuery("delete from User u where u.id=:id")
                .setParameter("id", id)
                .executeUpdate();
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
