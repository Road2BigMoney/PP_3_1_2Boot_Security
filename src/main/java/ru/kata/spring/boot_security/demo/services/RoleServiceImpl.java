package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleDAO;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleDAO roleDAO;

    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public Role findRoleByName(String name) {
        return roleDAO.findRoleByName(name);
    }

    @Override
    public List<Role> findAll() {
        return roleDAO.findAll();
    }

    @Override
    public Role findOne(Integer id) {
        return roleDAO.findOne(id);
    }

    @Override
    public List<Role> findMultipleById(Collection<Integer> idCollection) {
        return roleDAO.findMultipleById(idCollection);
    }
    @Override
    public Set<Role> getSetRolesByRoleNames(List<String> roleNames) {

        return roleDAO.getSetRolesByRoleNames(roleNames);
    }

    @Override
    public void save(Role role) {
        roleDAO.save(role);
    }

    @Override
    public void delete(Integer id) {
        roleDAO.delete(id);
    }

    @Override
    public void update(Role role) {
        roleDAO.update(role);
    }
}
