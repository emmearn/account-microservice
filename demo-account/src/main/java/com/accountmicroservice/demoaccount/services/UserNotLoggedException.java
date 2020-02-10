package com.accountmicroservice.demoaccount.services;

public class UserNotLoggedException extends Exception {
    public UserNotLoggedException(String message) {
        super(message);
    }
}
