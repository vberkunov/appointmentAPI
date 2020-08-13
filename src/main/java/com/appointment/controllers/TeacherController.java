package com.appointment.controllers;

import com.appointment.entity.Contract;
import com.appointment.entity.Lesson;
import com.appointment.entity.Student;
import com.appointment.entity.Teacher;
import com.appointment.payload.request.LessonRequest;
import com.appointment.payload.response.ParticipantResponse;
import com.appointment.services.implementation.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;



import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    @Autowired
    private TeacherServiceImpl teacherService;

    @Autowired
    private LessonServiceImpl lessonService;

    @Autowired
    private ContractServiceImpl contractService;

    @Autowired
    private StudentServiceImpl studentService;



    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/create")
    public ResponseEntity<?> createNewLesson(@Valid @RequestBody LessonRequest lessonRequest){

        Teacher currentTeacher = teacherService.getCurrentTeacher();

        Lesson newLesson = new Lesson(currentTeacher,lessonRequest.getDescription(),
                lessonRequest.getTimeFrom(),
                lessonRequest.getTimeTo(),
                lessonRequest.getPrice(),
                lessonRequest.getReservedDate());

        lessonService.save(newLesson);

        return ResponseEntity.ok("Create new lesson" +"\n"
                + "time from: " + lessonRequest.getTimeFrom()
                + "\n time to:" + lessonRequest.getTimeTo()
                + "date: " + lessonRequest.getReservedDate());
    }

    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/participants")
    public ResponseEntity<?> getAllParticipants(){

        List<Student> participants =  contractService.getAllContracts()
                .stream()
                .map(Contract::getStudent)
                .collect(Collectors.toList());
        List<ParticipantResponse> allParticipants = participants.
                stream()
                .map(s ->studentService.getParticipant(s.getUser().getId()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(allParticipants);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/approve/{id}")
    public ResponseEntity<?> approveParticipant(@PathVariable("id") Long id) throws NotFoundException {
        contractService.approve(id);
       return ResponseEntity.ok("Approving is successful");
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/decline/{id}")
    public ResponseEntity<?> declineParticipant(@PathVariable("id") Long id) throws NotFoundException {
        contractService.decline(id);
        return ResponseEntity.ok("Declining is successful!");
    }


}
