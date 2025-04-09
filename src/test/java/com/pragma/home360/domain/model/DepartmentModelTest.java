package com.pragma.home360.domain.model;

import com.pragma.home360.domain.exceptions.DescriptionMaxSizeExceededException;
import com.pragma.home360.domain.exceptions.NameMaxSizeExceededException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentModelTest {

    private static final String VALID_NAME = "Valle del cauca";
    private static final String VALID_DESC = "Departamento del valle del cauca";
    private static final String LONG_NAME = "A".repeat(51);
    private static final String LONG_DESC = "B".repeat(121);

    @Test
    void constructor_WithValidParameters_CreatesInstance() {
        // Act & Assert
        assertDoesNotThrow(() -> new DepartmentModel(1L, VALID_NAME, VALID_DESC));
    }

    @Test
    void constructor_NameTooLong_ThrowsException() {
        // Act & Assert
        Executable action = () -> new DepartmentModel(1L, LONG_NAME, VALID_DESC);
        assertThrows(NameMaxSizeExceededException.class, action);
    }

    @Test
    void constructor_DescriptionTooLong_ThrowsException() {
        // Act & Assert
        Executable action = () -> new DepartmentModel(1L, VALID_NAME, LONG_DESC);
        assertThrows(DescriptionMaxSizeExceededException.class, action);
    }



    @Test
    void setName_ValidValue_UpdatesField() {
        // Arrange
        DepartmentModel model = new DepartmentModel(1L, VALID_NAME, VALID_DESC);
        String newName = "Marketing";

        // Act
        model.setName(newName);

        // Assert
        assertEquals(newName, model.getName());
    }

    @Test
    void setName_TooLong_ThrowsException() {
        // Arrange
        DepartmentModel model = new DepartmentModel(1L, VALID_NAME, VALID_DESC);

        // Act & Assert
        assertThrows(NameMaxSizeExceededException.class,
                () -> model.setName(LONG_NAME));
    }


    @Test
    void setDescription_ValidValue_UpdatesField() {
        // Arrange
        DepartmentModel model = new DepartmentModel(1L, VALID_NAME, VALID_DESC);
        String newDesc = "Nueva descripción";

        // Act
        model.setDescription(newDesc);

        // Assert
        assertEquals(newDesc, model.getDescription());
    }

    @Test
    void setDescription_TooLong_ThrowsException() {
        // Arrange
        DepartmentModel model = new DepartmentModel(1L, VALID_NAME, VALID_DESC);

        // Act & Assert
        assertThrows(DescriptionMaxSizeExceededException.class,
                () -> model.setDescription(LONG_DESC));
    }

    @Test
    void getId_ReturnsCorrectValue() {
        // Arrange
        Long expectedId = 5L;
        DepartmentModel model = new DepartmentModel(expectedId, VALID_NAME, VALID_DESC);

        // Act & Assert
        assertEquals(expectedId, model.getId());
    }

    @Test
    void setId_UpdatesValue() {
        // Arrange
        DepartmentModel model = new DepartmentModel(1L, VALID_NAME, VALID_DESC);
        Long newId = 10L;

        // Act
        model.setId(newId);

        // Assert
        assertEquals(newId, model.getId());
    }
}