package com.appointment.services;

import com.appointment.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StudentService {
    Student register(Student student);

    List<Student> getAll();

    Student findByUsername(String username);

    Student findById(Long id);

    void delete(Long id);
}

