package com.appointment.controllers;

import com.appointment.entity.Contract;
import com.appointment.entity.Lesson;
import com.appointment.entity.Student;
import com.appointment.entity.Teacher;
import com.appointment.payload.request.LessonRequest;
import com.appointment.payload.response.ParticipantResponse;
import com.appointment.services.implementation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;



import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/teacher")
@Api(value="onlineuniversity", description="Operations pertaining to teacher functionality. ")
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
    @ApiOperation(value = "Create a new lesson.", response = Iterable.class)
    public ResponseEntity<?> createNewLesson(@Valid @RequestBody LessonRequest lessonRequest) throws Exception {

        Teacher currentTeacher = teacherService.getCurrentTeacher();

        if(lessonRequest.getTimeTo().before(lessonRequest.getTimeFrom())){
            throw new Exception("Not correct time period");
        }

        List<Time> date = lessonService.findAllByTeacher(currentTeacher).stream()
                .map(l -> l.getTimeFrom())
                .filter(l -> l.after(lessonRequest.getTimeFrom()))
                .collect(Collectors.toList());

        if(date.size() > 0)  {
            throw new Exception("Periods overlap.");
        }

        else {

            Lesson newLesson = new Lesson(currentTeacher, lessonRequest.getDescription(),
                    lessonRequest.getTimeFrom(),
                    lessonRequest.getTimeTo(),
                    lessonRequest.getPrice(),
                    lessonRequest.getReservedDate());
            lessonService.save(newLesson);

            return ResponseEntity.ok("Create new lesson" + "\n"
                    + "time from: " + lessonRequest.getTimeFrom()
                    + "\n time to:" + lessonRequest.getTimeTo()
                    + "date: " + lessonRequest.getReservedDate());
        }

    }

    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("/participants")
    @ApiOperation(value = "Get a list of students wishing to attend the lesson.", response = Iterable.class)
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
    @ApiOperation(value = "Approve the student by his identifier ", response = Iterable.class)
    public ResponseEntity<?> approveParticipant(@PathVariable("id") Long id) throws NotFoundException {
        contractService.approve(id);
       return ResponseEntity.ok("Approving is successful");
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/decline/{id}")
    @ApiOperation(value = "Decline the student by his identifier ", response = Iterable.class)
    public ResponseEntity<?> declineParticipant(@PathVariable("id") Long id) throws NotFoundException {
        contractService.decline(id);
        return ResponseEntity.ok("Declining is successful!");
    }


}
