package com.exercise.demomicroservice.models;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        User user = (User) obj;
        if (user.getPassword().length() < 8)
            errors.rejectValue("password", "the password must be at least 8 chars long!");
    }
}
