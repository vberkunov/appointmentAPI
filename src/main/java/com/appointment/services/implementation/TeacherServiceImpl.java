package com.appointment.services.implementation;

import com.appointment.entity.Role;
import com.appointment.entity.Student;
import com.appointment.entity.Teacher;
import com.appointment.repositories.RoleRepository;
import com.appointment.repositories.TeacherRepository;
import com.appointment.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.teacherRepository = teacherRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Teacher register(Teacher teacher) {
        Role role = roleRepository.findByName("ROLE_USER");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);

        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        teacher.setRoles(userRoles);


        Teacher registeredTeacher = teacherRepository.save(teacher);


        return registeredTeacher;
    }

    @Override
    public List<Teacher> getAll() {
        List<Teacher> result = teacherRepository.findAll();
        return result;
    }

    @Override
    public Teacher findByUsername(String username) {
        Teacher result = teacherRepository.findByUsername(username);
        return result;
    }

    @Override
    public Teacher findById(Long id) {
        Teacher result = teacherRepository.findById(id).orElse(null);

        if (result == null){
            return null;
        }
        return result;
    }

    @Override
    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }
}
