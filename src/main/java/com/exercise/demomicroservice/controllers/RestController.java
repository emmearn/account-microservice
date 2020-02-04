package com.exercise.demomicroservice.controllers;

import com.exercise.demomicroservice.models.JsonResponseBody;
import com.exercise.demomicroservice.models.Operation;
import com.exercise.demomicroservice.models.User;
import com.exercise.demomicroservice.models.UserValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

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

    @RequestMapping(value = "/login", method = POST)
    public ResponseEntity<JsonResponseBody> loginUser(@RequestParam String id,
                                                      @RequestParam(value="password") String pwd) {
        return null;
    }

    @RequestMapping(value = "/operations/account/{account}")
    public ResponseEntity<JsonResponseBody> fetchAllOperationsPerAccount(HttpServletRequest request,
                                                                         @PathVariable String account) {
        return null;
    }

    @RequestMapping(value = "/accounts/user")
    public ResponseEntity<JsonResponseBody> fetchAllAccountsPerUser(HttpServletRequest request) {
        return null;
    }

    @RequestMapping(value = "/operations", method = POST)
    public ResponseEntity<JsonResponseBody> addOperation(HttpServletRequest request,
                                                         @Valid Operation operation,
                                                         BindingResult bindingResult) {
        return null;
    }
}
