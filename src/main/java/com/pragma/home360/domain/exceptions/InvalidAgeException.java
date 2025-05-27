package com.pragma.home360.domain.exceptions;

public class InvalidAgeException extends RuntimeException {
    public InvalidAgeException() {
        super("The user must be at least 18 years old.");
    }
}
