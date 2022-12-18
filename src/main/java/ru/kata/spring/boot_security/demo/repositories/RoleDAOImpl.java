package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role findRoleByName(String name) {
        return entityManager.createQuery("select r from Role  r where r.name =:name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public List<Role> findAll() {
        return entityManager.createQuery("select r from Role r", Role.class).getResultList();
    }

    @Override
    public Role findOne(Integer id) {
        return entityManager.createQuery("select r from Role r where r.id=:id", Role.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Role> findMultipleById(Collection<Integer> idCollection) {
        List<Role> roles = new ArrayList<>();
        for (Integer id : idCollection) {
            roles.add(findOne(id));
        }
        return roles;
    }

    @Override
    public Set<Role> getSetRolesByRoleNames(List<String> roleNames) {
        Set<Role> roles = new HashSet<>();
        roleNames.forEach(roleName -> roles.add(findRoleByName(roleName)));
        return roles;
    }

    @Override
    public void save(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void delete(Integer id) {
        entityManager.createQuery("delete from Role where id =:id").setParameter("id", id);
    }

    @Override
    public void update(Role role) {
        entityManager.merge(role);
    }
}
