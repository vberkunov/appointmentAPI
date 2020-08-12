package com.appointment.payload.response;

import lombok.Data;


@Data
public class ParticipantResponse {

    private Long id;

    private String username;
    private String email;
    private String firstName;
    private String lastName;

    public ParticipantResponse(Long id, String username, String email, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
