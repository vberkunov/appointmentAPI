package com.appointment.payload.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
public class SignupRequestDto {

    @NotBlank(message = "Please provide an email")
    @Size(max = 50,message = "Email max size 50 characters")
    @Email(message = "Email not correct")
    private String email;
    @NotBlank(message = "Please provide a username")
    @Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters ")
    private String username;
    @NotBlank (message = "Please provide a password")
    @Size(min = 6, max = 40, message = "Password must be between 6 and 40 characters")
    private String password;
    @NotBlank(message = "Please provide a first name")
    @Size(min = 2, max = 20, message = "First name must be between 2 and 20 characters")
    private String firstName;
    @NotBlank(message = "Please provide a last name")
    @Size(min = 2, max = 20, message = "Last name must be between 2 and 20 characters")
    private String lastName;
    @NotBlank(message = "Please provide a role: student or teacher")
    private String role;

}
