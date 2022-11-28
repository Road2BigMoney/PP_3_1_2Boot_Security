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
    private final RoleService roleService;

    @Autowired
    public RoleServiceImpl(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public Role findRoleByName(String name) {
        return roleService.findRoleByName(name);
    }

    @Override
    public List<Role> findAll() {
        return roleService.findAll();
    }

    @Override
    public Role findOne(Integer id) {
        return roleService.findOne(id);
    }

    @Override
    public List<Role> findMultipleById(Collection<Integer> idCollection) {
        return roleService.findMultipleById(idCollection);
    }
//    @Override
//    public Set<Role> findRolesByNames(Set<String> roleNames) {
//        return
//    }

    @Override
    public void save(Role role) {
        roleService.save(role);
    }

    @Override
    public void delete(Integer id) {
        roleService.delete(id);
    }

    @Override
    public void update(Role role) {
        roleService.update(role);
    }
}
