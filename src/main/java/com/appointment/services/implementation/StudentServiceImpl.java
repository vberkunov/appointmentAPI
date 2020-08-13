package com.appointment.services.implementation;

import com.appointment.entity.Student;
import com.appointment.payload.response.ParticipantResponse;
import com.appointment.repositories.StudentRepository;
import com.appointment.repositories.UserRepository;
import com.appointment.services.StudentService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;



@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final UserRepository userRepository;

    public StudentServiceImpl(StudentRepository studentRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    public ParticipantResponse getParticipant(Long id){
       return studentRepository.getParticipant(id);
    }

    @Override
    public Student getCurrentStudent() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Student currentStudent = studentRepository.findByUser( userRepository.findByUsername(userDetails.getUsername()));
        return currentStudent;
    }
}
