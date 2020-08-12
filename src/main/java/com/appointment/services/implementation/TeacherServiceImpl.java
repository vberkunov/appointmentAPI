package com.appointment.services.implementation;

import com.appointment.entity.Teacher;
import com.appointment.entity.User;
import com.appointment.repositories.TeacherRepository;
import com.appointment.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl {

    private final UserRepository userRepository;

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(UserRepository userRepository, TeacherRepository teacherRepository) {
        this.userRepository = userRepository;

        this.teacherRepository = teacherRepository;
    }


    public Teacher getCurrentTeacher() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        User currentUser = userRepository.findByUsername(userDetails.getUsername());
        Teacher currentTeacher = teacherRepository.findByUser(currentUser);
        return currentTeacher;
    }
}
