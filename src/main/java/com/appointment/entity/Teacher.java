package com.appointment.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Builder
@Table(name = "teacher")
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "teacher",  cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Set<Lesson> lessons;



}
