package com.appointment.validator;

import com.appointment.entity.Lesson;
import com.appointment.services.implementation.LessonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Date;

@Component
public class LessonValidator implements Validator {

    @Autowired
    private LessonServiceImpl lessonService;


    @Override
    public boolean supports(Class<?> aClass) {
        return Lesson.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Lesson lesson = (Lesson) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"description","Required");
        if(lesson.getDescription().length() < 25 || lesson.getDescription().length() > 200){
            errors.rejectValue("description","Size.lessonForm.description");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"timeFrom","Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"timeTo","Required");
        if(lesson.getTimeTo().before(lesson.getTimeFrom())){
            errors.rejectValue("timeTo","Time.lessonForm.timeTo");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"price","Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"date","Required");
        long millis=System.currentTimeMillis();
        Date date=new Date(millis);
        if(lesson.getReservedDate().before(date)){
            errors.rejectValue("date","Date.lessonForm.date");
        }

    }
}
