package com.exercise.demomicroservice.controllers;

import com.exercise.demomicroservice.models.User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello everyone!";
    }

    @RequestMapping("user")
    public String addUser(User user) {
        return "User added correctly: " + user.getId() + ", " + user.getUsername();
    }

    @RequestMapping("user2")
    public String addUser2(@Valid User user) {
        return "User added correctly: " + user.getId() + ", " + user.getUsername();
    }

    @RequestMapping("user3")
    public String addUser3(@Valid User user, BindingResult result) {
        if(result.hasErrors())
            return result.toString();
        return "User added correctly: " + user.getId() + ", " + user.getUsername();
    }

    @RequestMapping("user4")
    public String addUser4(User user, BindingResult result) {
        UserValidator userValidator = new UserValidator();
        userValidator.validate(user, result);

        if(result.hasErrors())
            return result.toString();
        return "User added correctly: " + user.getId() + ", " + user.getUsername();
    }

    // INNER CLASS
    private class UserValidator implements Validator {
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
}
