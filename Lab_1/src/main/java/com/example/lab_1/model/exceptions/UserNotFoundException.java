package com.example.lab_1.model.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(" User not found: " + message);
    }
}
