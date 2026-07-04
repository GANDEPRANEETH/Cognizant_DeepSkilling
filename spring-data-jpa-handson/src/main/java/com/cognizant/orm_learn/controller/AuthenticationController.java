package com.cognizant.orm_learn.controller;
import java.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn.security.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);
@Autowired
private JwtUtil jwtUtil;
    @GetMapping("/authenticate")
    public Map<String, String> authenticate(@RequestHeader("Authorization") String authHeader) {
        LOGGER.info("Start: authenticate method");
        LOGGER.debug("Authorization Header: {}", authHeader);

        // Replace lines 21-23 with:
    String username = getUser(authHeader);
    Map<String, String> authMap = new HashMap<>();
    String token = jwtUtil.generateToken(username);
    authMap.put("token", token);
    authMap.put("username", username);
        LOGGER.info("End: authenticate method");
        return authMap;
    }
    private String getUser(String authHeader) {
        String encodedCredentials = authHeader.substring(6);
        byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);
        String credentials = new String(decodedBytes);
        String username = credentials.split(":")[0];
        LOGGER.debug("Decoded username: " + username);
        return username;
    }
}