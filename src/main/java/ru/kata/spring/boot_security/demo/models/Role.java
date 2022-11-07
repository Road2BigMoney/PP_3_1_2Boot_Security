package ru.kata.spring.boot_security.demo.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {
    @Id
    private int id;
    private String name;
    @Transient
    @ManyToMany(mappedBy = "user")
    private Set<User> users;
}
