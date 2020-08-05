package com.appointment.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Table(name = "student")
@Entity
public class Student extends User {

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Participant participant;
}
