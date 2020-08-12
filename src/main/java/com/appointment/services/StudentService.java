package com.appointment.services;

import com.appointment.entity.User;
import com.appointment.payload.response.ParticipantResponse;




public interface StudentService {
    ParticipantResponse getParticipant(Long id);

}

