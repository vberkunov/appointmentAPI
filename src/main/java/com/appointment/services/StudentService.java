package com.appointment.services;

import com.appointment.entity.Student;
import com.appointment.payload.response.ParticipantResponse;




public interface StudentService {
    ParticipantResponse getParticipant(Long id);

    Student getCurrentStudent();
}

