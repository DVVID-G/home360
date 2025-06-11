package com.pragma.home360.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationModelTest {


    @Test
    void getId() {
        // Arrange
        LocationModel locationModel = new LocationModel(1l, new CityModel(1L, "Cali", "ciudad ubicada en el Valle del cauca", new DepartmentModel(1L, "Valle del Cauca", "Departamento del Valle del Cauca")), "Barrio 1");
        Long expectedId = 1L;
        locationModel.setId(expectedId);

        // Act
        Long actualId = locationModel.getId();

        // Assert
        assertEquals(expectedId, actualId);

    }

    @Test
    void setId() {
        // Arrange
        LocationModel locationModel = new LocationModel(1l, new CityModel(1L, "Cali", "ciudad ubicada en el Valle del cauca", new DepartmentModel(1L, "Valle del Cauca", "Departamento del Valle del Cauca")), "Barrio 1");
        Long expectedId = 1L;

        // Act
        locationModel.setId(expectedId);

        // Assert
        assertEquals(expectedId, locationModel.getId());

    }

    @Test
    void getCity() {
        // Arrange
        LocationModel locationModel = new LocationModel(1l, new CityModel(1L, "Cali", "ciudad ubicada en el Valle del cauca", new DepartmentModel(1L, "Valle del Cauca", "Departamento del Valle del Cauca")), "Barrio 1");
        CityModel expectedCity = new CityModel(1L, "Cali", "ciudad ubicada en el Valle del cauca", new DepartmentModel(1L, "Valle del Cauca", "Departamento del Valle del Cauca"));
        locationModel.setCity(expectedCity);

        // Act
        CityModel actualCity = locationModel.getCity();

        // Assert
        assertEquals(expectedCity, actualCity);
    }

    @Test
    void setCity() {
        // Arrange
        LocationModel locationModel = new LocationModel(1l, new CityModel(1L, "Cali", "ciudad ubicada en el Valle del cauca", new DepartmentModel(1L, "Valle del Cauca", "Departamento del Valle del Cauca")), "Barrio 1");
        CityModel expectedCity = new CityModel(1L, "Cali", "ciudad ubicada en el Valle del cauca", new DepartmentModel(1L, "Valle del Cauca", "Departamento del Valle del Cauca"));

        // Act
        locationModel.setCity(expectedCity);

        // Assert
        assertEquals(expectedCity, locationModel.getCity());
    }

    @Test
    void getBarrio() {
        // Arrange
        LocationModel locationModel = new LocationModel(1l, new CityModel(1L, "Cali", "ciudad ubicada en el Valle del cauca", new DepartmentModel(1L, "Valle del Cauca", "Departamento del Valle del Cauca")), "Barrio 1");
        String expectedBarrio = "Barrio 1";
        locationModel.setBarrio(expectedBarrio);

        // Act
        String actualBarrio = locationModel.getBarrio();

        // Assert
        assertEquals(expectedBarrio, actualBarrio);
    }

    @Test
    void setBarrio() {
        // Arrange
        LocationModel locationModel = new LocationModel(1l, new CityModel(1L, "Cali", "ciudad ubicada en el Valle del cauca", new DepartmentModel(1L, "Valle del Cauca", "Departamento del Valle del Cauca")), "Barrio 1");
        String expectedBarrio = "Barrio 1";

        // Act
        locationModel.setBarrio(expectedBarrio);

        // Assert
        assertEquals(expectedBarrio, locationModel.getBarrio());
    }

    @Test
    void getCityModel() {
        // Arrange
        LocationModel locationModel = new LocationModel(1l, new CityModel(1L, "Cali", "ciudad ubicada en el Valle del cauca", new DepartmentModel(1L, "Valle del Cauca", "Departamento del Valle del Cauca")), "Barrio 1");
        CityModel expectedCity = new CityModel(1L, "Cali", "ciudad ubicada en el Valle del cauca", new DepartmentModel(1L, "Valle del Cauca", "Departamento del Valle del Cauca"));
        locationModel.setCity(expectedCity);

        // Act
        CityModel actualCity = locationModel.getCity();

        // Assert
        assertEquals(expectedCity, actualCity);
    }

    @Test
    void setCityModel() {
        // Arrange
        LocationModel locationModel = new LocationModel(1l, new CityModel(1L, "Cali", "ciudad ubicada en el Valle del cauca", new DepartmentModel(1L, "Valle del Cauca", "Departamento del Valle del Cauca")), "Barrio 1");
        CityModel expectedCity = new CityModel(1L, "Cali", "ciudad ubicada en el Valle del cauca", new DepartmentModel(1L, "Valle del Cauca", "Departamento del Valle del Cauca"));

        // Act
        locationModel.setCity(expectedCity);

        // Assert
        assertEquals(expectedCity, locationModel.getCity());
    }
}