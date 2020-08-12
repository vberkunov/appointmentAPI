package com.appointment.services.implementation;

import com.appointment.entity.Lesson;
import com.appointment.repositories.LessonRepository;
import com.appointment.services.LessonService;
import org.springframework.stereotype.Service;

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
}
