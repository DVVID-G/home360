package com.pragma.home360.domain.exceptions;

public class DepartmentCannotBeNullException extends RuntimeException {
    public DepartmentCannotBeNullException(String message) {
        super(message);
    }
}
