package com.appointment.security.jwt;

import com.appointment.entity.Role;
import com.appointment.entity.Status;
import com.appointment.entity.Student;
import com.appointment.entity.Teacher;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class JwtUserFactory {
    public JwtUserFactory() {
    }

    public static JwtUser create(Student student) {
        return new JwtUser(
                student.getId(),
                student.getUsername(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getPassword(),
                mapToGrantedAuthorities(new HashSet<>(student.getRoles())),
                student.getStatus().equals(Status.ACTIVE),
                student.getUpdated()


        );
    }
    public static JwtUser create(Teacher teacher) {
        return new JwtUser(
                teacher.getId(),
                teacher.getUsername(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getEmail(),
                teacher.getPassword(),
                mapToGrantedAuthorities(new HashSet<>(teacher.getRoles())),
                teacher.getStatus().equals(Status.ACTIVE),
                teacher.getUpdated()

                );
    }

    private static Set<GrantedAuthority> mapToGrantedAuthorities(Set<Role> userRoles) {
        return userRoles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toSet());
    }
}
