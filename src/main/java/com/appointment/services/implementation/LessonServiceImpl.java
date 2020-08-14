package com.appointment.services.implementation;

import com.appointment.entity.Lesson;
import com.appointment.entity.Teacher;
import com.appointment.repositories.LessonRepository;
import com.appointment.services.LessonService;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    public LessonServiceImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public void save(Lesson newLesson) {
        lessonRepository.save(newLesson);
    }

    @Override
    public List<Lesson> getAll() {
        return lessonRepository.findAll();
    }

    @Override
    public Lesson findById(Long id) {
        return lessonRepository.findById(id).orElse(null);
    }

    @Override
    public List<Lesson> findAllByTeacher(Teacher teacher) {
        return lessonRepository.findAllByTeacher(teacher);
    }
}
