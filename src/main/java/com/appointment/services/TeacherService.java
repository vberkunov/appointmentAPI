package com.appointment.services;


import com.appointment.entity.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TeacherService {
    Teacher register(Teacher teacher);

    List<Teacher> getAll();

    Teacher findByUsername(String username);

    Teacher findById(Long id);

    void delete(Long id);
}
