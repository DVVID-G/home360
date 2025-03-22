package com.pragma.home360.domain.utils.constants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class DomainConstantsTest  {
    @Test
    void testConstructorThrowsException() {
    // Obtenemos el constructor privado de la clases
        Constructor<?>[] constructors = DomainConstants.class.getDeclaredConstructors();
    // Esperamos que solo haya un constructor.
        assertEquals(1, constructors.length);
        Constructor<?> constructor = constructors[0];
    // Permitir el acceso al constructor privado.
        constructor.setAccessible(true);
    // Intentamos instanciar la clase y esperamos que lance una excepción.
        Exception exception = assertThrows(InvocationTargetException.class, () -> {
            constructor.newInstance();
    });
    // Verificamos que la causa de la excepción sea una IllegalStateException
        assertTrue(exception.getCause() instanceof IllegalStateException);
    // Verificamos que el mensaje sea "Utility class"
        assertEquals("Utility class", exception.getCause().getMessage());
            }
}
