package com.appointment.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "lesson")
@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(targetEntity = Teacher.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id", nullable = false)
    private Teacher teacher;
    @Temporal(TemporalType.TIME)
    private String description;
    private Date reservedTime;
    @Temporal(TemporalType.DATE)
    private Date reservedDate;
    @OneToOne(mappedBy = "lesson", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Participant participant;



}
