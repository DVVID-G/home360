package com.pragma.home360.domain.exceptions;

public class InvalidDocumentException extends RuntimeException {
    public InvalidDocumentException() {
        super("the document number must be contained only by numbers");
    }
}
