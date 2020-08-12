package com.appointment.controllers;


import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/student")
public class StudentController {

    @GetMapping("/lessons")
    public String getAllLessons() {
        return "Public Content.";
    }


}
