package com.exercise.demomicroservice.utils;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    public static String generateJwt(String subject, Date date, String name, String scope) throws UnsupportedEncodingException {
        String jwt = Jwts.builder()
                .setSubject(subject)
                .setExpiration(date)
                .claim("name", name)
                .claim("scope", scope)
                .signWith(
                        SignatureAlgorithm.HS256,
                        "myPersonalSecretKey12345".getBytes("UTF-8")
                ).compact();
        return jwt;
    }

    public static Map<String, Object> jwt2Map(String jwt) throws UnsupportedEncodingException, ExpiredJwtException {
        Jws<Claims> claim = Jwts.parser()
                .setSigningKey("myPersonalSecretKey12345".getBytes("UTF-8"))
                .parseClaimsJws(jwt);

        String name = claim.getBody().get("name", String.class);
        String scope = (String) claim.getBody().get("scope");

        Date expDate = claim.getBody().getExpiration();
        String subj = claim.getBody().getSubject();

        Date now = new Date();
        if(now.after(expDate))
            throw new ExpiredJwtException(null, null, "Session expired");

        Map<String, Object> userData = new HashMap<>();
        userData.put("name", name);
        userData.put("scope", scope);
        userData.put("expDate", expDate);
        userData.put("subj", subj);

        return userData;
    }

    public static String getJwtFromHttpRequest(HttpServletRequest request) {
        String jwt = null;

        if(request.getHeader("jwt") != null)
            jwt = request.getHeader("jwt");
        else if(request.getCookies() != null)
            for(Cookie cookie: request.getCookies())
                if(cookie.getName().equals("jwt"))
                    jwt = cookie.getValue();

        return jwt;
    }
}
