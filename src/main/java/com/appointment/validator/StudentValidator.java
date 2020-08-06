package com.appointment.validator;

import com.appointment.entity.Student;
import com.appointment.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class StudentValidator implements Validator {

    @Autowired
    private StudentService service;

    @Override
    public boolean supports(Class<?> aClass) {
        return Student.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Student student = (Student) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email","Required");
        if(student.getEmail().length() < 8 || student.getEmail().length() > 35){
            errors.rejectValue("email","Size.userForm.email");
        }
        if (service.findByUsername(student.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (student.getPassword().length() < 8 || student.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

    }
}
