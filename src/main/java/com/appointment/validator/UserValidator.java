package com.appointment.validator;

import com.appointment.entity.Student;
import com.appointment.entity.User;
import com.appointment.services.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
@Component
public class UserValidator implements Validator {

    @Autowired
    private UserServiceImpl service;

    @Override
    public boolean supports(Class<?> aClass) {
        return Student.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email","Required");
        if(user.getEmail().length() < 8 || user.getEmail().length() > 35){
            errors.rejectValue("email","Size.userForm.email");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"username","Required");
        if (service.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

    }
}
