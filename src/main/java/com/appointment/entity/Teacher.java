package com.appointment.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Table(name = "teacher")
@Entity
public class Teacher extends User {

    @OneToMany(mappedBy = "teacher",  cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Lesson> lessons;

    @ManyToMany
    @JoinTable(name = "teacher_roles", joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;


}
