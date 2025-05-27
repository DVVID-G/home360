package com.pragma.home360.domain.exceptions;

public class InvalidPhoneException extends RuntimeException {
    public InvalidPhoneException() {
        super("the phone number must be 13 digits long and start with a valid country code (e.g., +57 for Colombia).");
    }
}
