package com.appointment.services;

import com.appointment.entity.User;

import java.util.List;

public interface UserService {

    User createStudent(User user);

    User createTeacher(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void delete(Long id);

    User findByEmail(String email);
}
