package com.pragma.home360.domain.exceptions;

public class InvalidStatusException extends RuntimeException {
    public InvalidStatusException(String status
    ) {
        super("Status inválido: '" + status + "'. Valores permitidos: PUBLICADA, PUBLICACION_PAUSADA, TRASACCION_CURSO, TRANSACCION_FINALIZADA"
        );
    }
}
