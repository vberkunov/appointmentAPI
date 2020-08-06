package com.appointment.security;

import com.appointment.entity.Student;
import com.appointment.security.jwt.JwtUser;
import com.appointment.security.jwt.JwtUserFactory;
import com.appointment.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StudentDetailService implements UserDetailsService {

    private final StudentService studentService;

    @Autowired
    public StudentDetailService(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentService.findByUsername(username);

        if(student == null){
            throw new UsernameNotFoundException("Student with username" + username + "not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(student);
        return jwtUser;
    }
}
