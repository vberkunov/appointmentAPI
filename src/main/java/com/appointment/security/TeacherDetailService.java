package com.appointment.security;

import com.appointment.entity.Teacher;
import com.appointment.security.jwt.JwtUser;
import com.appointment.security.jwt.JwtUserFactory;
import com.appointment.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TeacherDetailService implements UserDetailsService {

    private final TeacherService teacherService;

    @Autowired
    public TeacherDetailService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Teacher teacher = teacherService.findByUsername(username);

        if(teacher == null){
            throw new UsernameNotFoundException("Teacher with username" + username + "not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(teacher);
        return jwtUser;
    }
}
