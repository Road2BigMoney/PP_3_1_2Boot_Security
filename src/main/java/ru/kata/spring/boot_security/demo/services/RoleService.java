package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public void saveRole(Role role) {
        roleRepository.save(role);
    }
    public Role getRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }
    public Role findById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }
    public Set<Role> getAllRoles(){
        return new HashSet<>(roleRepository.findAll());
    }
}
