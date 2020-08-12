package com.appointment.repositories;

import com.appointment.entity.Student;
import com.appointment.payload.response.ParticipantResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findById(Long id);

    @Query("SELECT new com.appointment.payload.response.ParticipantResponse(s.id, u.username, u.email , u.firstName, u.lastName) FROM Student s JOIN s.user u WHERE u.id = :id")
    ParticipantResponse getParticipant(Long id);

}
