package com.appointment.controllers;

import com.appointment.dto.JwtResponse;
import com.appointment.dto.SigninRequestDto;
import com.appointment.dto.SignupRequestDto;
import com.appointment.entity.Role;
import com.appointment.entity.Status;
import com.appointment.entity.Student;
import com.appointment.security.jwt.JwtUtils;
import com.appointment.security.services.StudentDetails;
import com.appointment.services.RoleService;
import com.appointment.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class StudentController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    StudentService studentService;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SigninRequestDto requestDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        StudentDetails studentDetails = (StudentDetails) authentication.getPrincipal();
        Set<String> roles = studentDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toSet());

        return ResponseEntity.ok(new JwtResponse(jwt,
                studentDetails.getId(),
                studentDetails.getUsername(),
                studentDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequestDto requestDto) {
        if (studentService.findByUsername(requestDto.getUsername())!= null) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }

        if (studentService.findByEmail(requestDto.getEmail())!= null) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }

        // Create new user's account
        Student student = new Student();
        student.setUsername(requestDto.getUsername());
        student.setEmail(requestDto.getEmail());
        student.setPassword(encoder.encode(requestDto.getPassword()));
        student.setFirstName(requestDto.getFirstName());
        student.setLastName(requestDto.getLastName());

        Set<String> strRoles = requestDto.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleService.findByName("ROLE_USER");
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleService.findByName("ROLE_ADMIN");

                        roles.add(adminRole);

                        break;

                    default:
                        Role userRole = roleService.findByName("ROLE_USER");
                        roles.add(userRole);
                }
            });
        }

        student.setRoles(roles);
        studentService.register(student);

        return ResponseEntity.ok("User registered successfully!");
    }
}