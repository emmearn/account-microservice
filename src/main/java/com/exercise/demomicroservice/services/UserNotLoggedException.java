package com.exercise.demomicroservice.services;

public class UserNotLoggedException extends Exception {
    public UserNotLoggedException(String message) {
        super(message);
    }
}
