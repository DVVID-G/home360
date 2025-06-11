package com.pragma.home360.domain.exceptions;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class NameMaxSizeExceededExceptionTest {

    @Test
    void testExceptionIsInstanceOfRuntimeException() {
        // Act: Creamos una instancia de la excepción
        NameMaxSizeExceededException exception = new NameMaxSizeExceededException();

        // Assert: Verificamos que la excepción es una subclase de RuntimeException
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void testThrowingException() {
        // Act & Assert: Verificamos que la excepción puede ser lanzada y capturada
        Exception exception = assertThrows(NameMaxSizeExceededException.class, () -> {
            throw new NameMaxSizeExceededException();
        });

        // Verificamos que el mensaje es nulo porque no se define en el constructor
        assertNull(exception.getMessage());
    }
}
