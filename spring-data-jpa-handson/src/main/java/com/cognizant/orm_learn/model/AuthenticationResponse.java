package com.cognizant.orm_learn.model;

public class AuthenticationResponse {
    
    private final String jwt;

    // Constructor to set the JWT token
    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    // Getter for the JWT token
    public String getJwt() {
        return jwt;
    }
}