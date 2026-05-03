package com.example.lab_1.model.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String message) {

        super("Username already exists: " + message);
    }
}
