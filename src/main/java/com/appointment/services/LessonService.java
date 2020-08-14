package com.appointment.services;


import com.appointment.entity.Lesson;
import com.appointment.entity.Teacher;

import java.util.List;

public interface LessonService  {
    void save(Lesson newLesson);

    List<Lesson> getAll();

    Lesson findById(Long id);

    List<Lesson> findAllByTeacher(Teacher teacher);
}
