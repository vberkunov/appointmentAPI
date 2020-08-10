package com.appointment.security.services;

import com.appointment.entity.Student;
import com.appointment.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentDetailsService implements UserDetailsService {

    @Autowired
    private final StudentService studentService;

    public StudentDetailsService(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    @Transactional
    public StudentDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentService.findByUsername(username);

        return StudentDetails.build(student);
    }
}
