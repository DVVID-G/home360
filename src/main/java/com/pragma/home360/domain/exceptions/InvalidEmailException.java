package com.pragma.home360.domain.exceptions;

public class InvalidEmailException extends RuntimeException {
  public InvalidEmailException() {
    super("The email must be a valid email address.");
  }
}
