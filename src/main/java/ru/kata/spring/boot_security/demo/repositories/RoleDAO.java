package ru.kata.spring.boot_security.demo.repositories;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.Collection;
import java.util.List;

public interface RoleDAO  {
    List<Role> findRoleByName(String name);

    public List<Role> findAll();

    public Role findOne(Integer id);

    public List<Role> findMultipleById(Collection<Integer> idCollection);

    public void save(Role role);

    public void delete(Integer id);

    void update(Role role);

}
