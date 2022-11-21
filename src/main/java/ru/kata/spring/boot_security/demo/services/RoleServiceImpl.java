package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleService;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements ru.kata.spring.boot_security.demo.services.RoleService {
    private final RoleService roleDAO;

    @Autowired
    public RoleServiceImpl(RoleService roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public List<Role> findRoleByName(String name) {
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
