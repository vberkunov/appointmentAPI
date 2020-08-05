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



}
