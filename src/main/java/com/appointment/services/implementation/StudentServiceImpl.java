package com.appointment.services.implementation;

import com.appointment.entity.User;
import com.appointment.payload.response.ParticipantResponse;
import com.appointment.repositories.StudentRepository;
import com.appointment.services.StudentService;
import org.springframework.stereotype.Service;



@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public ParticipantResponse getParticipant(Long id){
       return studentRepository.getParticipant(id);
    }
}
