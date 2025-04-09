package com.pragma.home360.domain.exceptions;

public class CityCannotBeNullException extends RuntimeException {
    public CityCannotBeNullException(String message) {
        super(message);
    }
}
