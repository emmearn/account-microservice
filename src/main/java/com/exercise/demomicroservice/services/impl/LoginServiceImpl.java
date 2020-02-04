package com.exercise.demomicroservice.services.impl;

import com.exercise.demomicroservice.models.User;
import com.exercise.demomicroservice.repo.UserRepo;
import com.exercise.demomicroservice.services.LoginService;
import com.exercise.demomicroservice.services.UserNotLoggedException;
import com.exercise.demomicroservice.utils.EncryptionUtils;
import com.exercise.demomicroservice.utils.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    UserRepo userRepo;

    @Autowired
    EncryptionUtils encryptionUtils;

    @Override
    public Optional<User> getUser(String id, String password) throws UserNotLoggedException {
        Optional<User> user = userRepo.findById(id);
        if (user.isPresent())
            if(encryptionUtils.decrypt(user.get().getPassword()).equals(password))
                log.info("Username and password verified");
            else {
                log.error("Username verified but password is wrong!");
                throw new UserNotLoggedException("User not correctly logged in");
            }
        return user;
    }

    @Override
    public String createJwt(String subject, String name, String permission) throws UnsupportedEncodingException {
        Date expDate = new Date();
        expDate.setTime(expDate.getTime() + (300*1000)); // 5 minutes
        log.info("Jwt Creation. Expiration: " + expDate.getTime());

        return JwtUtils.generateJwt(subject, expDate, name, permission);
    }

    @Override
    public Map<String, Object> verifyJwtAndGetData(HttpServletRequest request) throws UserNotLoggedException, UnsupportedEncodingException {
        String jwt = JwtUtils.getJwtFromHttpRequest(request);
        if (jwt == null)
            throw new UserNotLoggedException("Authentication token not found");

        return JwtUtils.jwt2Map(jwt);
    }
}
