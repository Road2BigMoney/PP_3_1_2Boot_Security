package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface RoleService {
    Role findRoleByName(String name);

    public List<Role> findAll();

    public Role findOne(Integer id);

    public List<Role> findMultipleById(Collection<Integer> idCollection);
    Set<Role> getSetRolesByRoleNames(List<String> roleNames);
    public void save(Role role);

    public void delete(Integer id);

    void update(Role role);
}
