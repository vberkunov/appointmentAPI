package com.appointment.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "student")
@Entity
public class Student extends User {

    @OneToOne(mappedBy = "student")
    private Participant participant;

    @ManyToMany
    @JoinTable(name = "student_roles", joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;



}
