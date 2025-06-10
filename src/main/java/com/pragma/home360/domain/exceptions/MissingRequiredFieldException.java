package com.pragma.home360.domain.exceptions;

public class MissingRequiredFieldException extends RuntimeException {
    public MissingRequiredFieldException(String fieldName
    ) {
        super("Campo requerido faltante: " + fieldName
        );
    }
}
