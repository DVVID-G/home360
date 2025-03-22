package com.pragma.home360.domain.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DescriptionMaxSizeExceededExceptionTest {

    @Test
    void testExceptionIsInstanceOfRuntimeException() {
        // Act: Creamos una instancia de la excepción
        DescriptionMaxSizeExceededException exception = new DescriptionMaxSizeExceededException();

        // Assert: Verificamos que la excepción es una subclase de RuntimeException
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void testThrowingException() {
        // Act & Assert: Verificamos que la excepción puede ser lanzada y capturada
        Exception exception = assertThrows(DescriptionMaxSizeExceededException.class, () -> {
            throw new DescriptionMaxSizeExceededException();
        });

        // Verificamos que el mensaje es nulo porque no se define en el constructor
        assertNull(exception.getMessage());
    }
}
