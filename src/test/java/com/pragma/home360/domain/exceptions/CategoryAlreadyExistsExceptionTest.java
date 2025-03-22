package com.pragma.home360.domain.exceptions;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CategoryAlreadyExistsExceptionTest {

    @Test
    void testExceptionIsInstanceOfRuntimeException() {
        // Act: Creamos una instancia de la excepción
        CategoryAlreadyExistsException exception = new CategoryAlreadyExistsException();

        // Assert: Verificamos que la excepción es una subclase de RuntimeException
        assertInstanceOf(RuntimeException.class, exception);
    }

    @Test
    void testThrowingException() {
        // Act & Assert: Verificamos que la excepción puede ser lanzada y capturada
        Exception exception = assertThrows(CategoryAlreadyExistsException.class, () -> {
            throw new CategoryAlreadyExistsException();
        });

        // Verificamos que el mensaje sea nulo (porque no hay un mensaje definido en el constructor)
        assertNull(exception.getMessage());
    }
}
