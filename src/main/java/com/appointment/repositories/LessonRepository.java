package com.appointment.repositories;

import com.appointment.entity.Lesson;
import com.appointment.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    Optional<Lesson> findById(Long id);

    List<Lesson> findAllByTeacher(Teacher teacher);
}
