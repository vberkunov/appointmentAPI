package com.appointment.controllers.auth;

import com.appointment.entity.User;
import com.appointment.payload.response.JwtResponse;
import com.appointment.payload.request.SigninRequestDto;
import com.appointment.payload.request.SignupRequestDto;

import com.appointment.security.jwt.JwtUtils;
import com.appointment.security.services.UserDetailsImpl;

import com.appointment.services.implementation.EmailServiceImpl;
import com.appointment.services.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private EmailServiceImpl emailService;

    @Autowired
    private JwtUtils jwtUtils;

    @Value("${mail.toStudent}")
    private String studentText;

    @Value("${mail.toTeacher}")
    private String teacherText;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SigninRequestDto requestDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Set<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toSet());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequestDto signUpRequest) throws MessagingException {
        if (userService.findByUsername(signUpRequest.getUsername()) != null) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }

        if (userService.findByEmail(signUpRequest.getEmail()) != null) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }

        User user = new User(signUpRequest.getEmail(),
                signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getFirstName(),
                signUpRequest.getLastName());
        String strRole = signUpRequest.getRole();

        if (strRole == null) {
            throw new RuntimeException("Error: Role is not found.");

        } else {

            switch (strRole) {
                case "student":
                    // Create new student's account

                    userService.createStudent(user);
                    emailService.sendMail(user.getEmail(),"Online University", studentText );

                    break;
                case "teacher":
                    // Create new teacher's account

                    userService.createTeacher(user);
                    emailService.sendMail(user.getEmail(),"Online University", teacherText);

                    break;

            }
        }


        return ResponseEntity.ok("User registered successfully!");
    }
}