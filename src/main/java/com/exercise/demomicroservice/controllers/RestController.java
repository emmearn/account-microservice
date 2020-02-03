package com.exercise.demomicroservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RestController {
    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello everyone!";
    }
}
