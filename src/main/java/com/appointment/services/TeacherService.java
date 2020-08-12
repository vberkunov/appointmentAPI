package com.appointment.services;


import com.appointment.entity.Teacher;



public interface TeacherService {


    Teacher findByUsername(String username);

    Teacher findById(Long id);

    void delete(Long id);

    Teacher findByEmail(String email);
}
