package com.appointment.controllers;

import com.appointment.dto.SigninRequestDto;
import com.appointment.dto.SignupRequestDto;
import com.appointment.entity.Role;
import com.appointment.entity.Student;
import com.appointment.security.jwt.JwtTokenProvider;
import com.appointment.services.RoleService;
import com.appointment.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/student")
public class StudentController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final StudentService studentService;

    private final RoleService roleService;

    @Autowired
    public StudentController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, StudentService studentService, RoleService roleService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.studentService = studentService;
        this.roleService = roleService;
    }



    @PostMapping("/signin")
    public ResponseEntity login(@RequestBody SigninRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            Student student = studentService.findByUsername(username);

            if (student == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, student.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequestDto requestDto) {
        if (studentService.findByUsername(requestDto.getUsername()) != null) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }

        if (studentService.findByEmail(requestDto.getEmail()) != null) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }

        // Create new user's account
        Student student = new Student();
        student.setUsername(requestDto.getUsername());
        student.setEmail(requestDto.getEmail());
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
