package com.exercise.demomicroservice.services;

import com.exercise.demomicroservice.models.User;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Optional;

public interface LoginService {

    Optional<User> getUser(String id, String password) throws UserNotLoggedException;
    String createJwt(String subject, String name, String permission) throws UnsupportedEncodingException;
    Map<String, Object> verifyJwtAndGetData(HttpServletRequest request) throws UserNotLoggedException, UnsupportedEncodingException;
}
