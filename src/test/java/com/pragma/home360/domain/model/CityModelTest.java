package com.pragma.home360.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityModelTest {
    private static final String VALID_NAME = "Cali";
    private static final String VALID_DESC = "ciudad ubicada en el Valle del cauca";

    private static final String LONG_NAME = "A".repeat(51);
    private static final String LONG_DESCRIPTION = "B".repeat(121);

    @Test
    void constructor_WithValidParameters_CreatesInstance() {
        // Act & Assert
        assertDoesNotThrow(() -> new CityModel(1L, VALID_NAME, VALID_DESC, new DepartmentModel(1L, "Valle del Cauca", "Departamento del Valle del Cauca")));
    }

    @Test
    void setId() {
        // Arrange
        CityModel cityModel = new CityModel();
        Long expectedId = 1L;

        // Act
        cityModel.setId(expectedId);

        // Assert
        assertEquals(expectedId, cityModel.getId());
    }

    @Test
    void getName() {
        // Arrange
        CityModel cityModel = new CityModel();
        String expectedName = "Cali";
        cityModel.setName(expectedName);

        // Act
        String actualName = cityModel.getName();

        // Assert
        assertEquals(expectedName, actualName);
    }

    @Test
    void setName() {
        // Arrange
        CityModel cityModel = new CityModel();
        String expectedName = "Cali";

        // Act
        cityModel.setName(expectedName);

        // Assert
        assertEquals(expectedName, cityModel.getName());
    }

    @Test
    void getDescription() {
        // Arrange
        CityModel cityModel = new CityModel();
        String expectedDescription = "ciudad ubicada en el Valle del cauca";
        cityModel.setDescription(expectedDescription);

        // Act
        String actualDescription = cityModel.getDescription();

        // Assert
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    void setDescription() {
        // Arrange
        CityModel cityModel = new CityModel();
        String expectedDescription = "ciudad ubicada en el Valle del cauca";

        // Act
        cityModel.setDescription(expectedDescription);

        // Assert
        assertEquals(expectedDescription, cityModel.getDescription());
    }

    @Test
    void getDepartment() {
        // Arrange
        CityModel cityModel = new CityModel();
        DepartmentModel expectedDepartment = new DepartmentModel(1L, "Valle del Cauca", "Departamento del Valle del Cauca");
        cityModel.setDepartment(expectedDepartment);

        // Act
        DepartmentModel actualDepartment = cityModel.getDepartment();

        // Assert
        assertEquals(expectedDepartment, actualDepartment);
    }


}