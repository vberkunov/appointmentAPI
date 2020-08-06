package com.appointment.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "student")
@Entity
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean confirmation;

    @OneToOne(targetEntity = Student.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Student student;

    @OneToOne(mappedBy = "participant", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, optional = false)
    private Lesson lesson;
}
