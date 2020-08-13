package com.appointment.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "lesson")
@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = Teacher.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id", nullable = false)
    private Teacher teacher;

    private String description;

    private Time timeFrom;

    private Time timeTo;

    private String price;

    private Date reservedDate;

    @OneToMany(mappedBy="lesson")
    private Set<Contract> contracts;

    public Lesson(Teacher teacher, String description, Time timeFrom, Time timeTo, String price, Date reservedDate) {
        this.teacher = teacher;
        this.description = description;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.price = price;
        this.reservedDate = reservedDate;
    }
}
