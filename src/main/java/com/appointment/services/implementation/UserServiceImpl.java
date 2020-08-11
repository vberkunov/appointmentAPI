package com.appointment.services.implementation;

import com.appointment.entity.Role;
import com.appointment.entity.Student;
import com.appointment.entity.Teacher;
import com.appointment.entity.User;
import com.appointment.repositories.RoleRepository;
import com.appointment.repositories.StudentRepository;
import com.appointment.repositories.TeacherRepository;
import com.appointment.repositories.UserRepository;
import com.appointment.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final StudentRepository studentRepository;

    private final TeacherRepository teacherRepository;

    private final RoleRepository roleRepository;



    @Autowired
    public UserServiceImpl(UserRepository userRepository, StudentRepository studentRepository, TeacherRepository teacherRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.roleRepository = roleRepository;
    }


    @Override
    public User createStudent(User user) {
        Role role = roleRepository.findByName("ROLE_STUDENT");
        user.setRole(role);
        Student student = new Student(user);
        studentRepository.save(student);
        User registeredUser = userRepository.save(user);

        return registeredUser;

    }
    @Override
    public User createTeacher(User user) {
        Role role = roleRepository.findByName("ROLE_TEACHER");
        user.setRole(role);
        Teacher teacher = new Teacher(user);
        teacherRepository.save(teacher);
        User registeredUser = userRepository.save(user);

        return registeredUser;
    }




    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result =userRepository.findById(id).orElse(null);

        if (result == null) {
            return null;
        }

        return result;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByEmail(String email) {
        User result = userRepository.findByEmail(email);
        return result;
    }


}
