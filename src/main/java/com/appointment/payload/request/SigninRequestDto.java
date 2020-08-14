package com.appointment.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SigninRequestDto {
   @NotBlank(message = "Please provide an username")
   @Size(min = 3, max = 20, message =  "Username must be between 3 and 20 characters")
   private String username;
   @NotBlank(message = "Please provide a password")
   @Size(min = 6, max = 40, message = "Password must be between 6 and 40 characters")
   private String password;
}
