package com.pragma.home360.domain.usecases;

import com.pragma.home360.domain.exceptions.CityCannotBeNullException;
import com.pragma.home360.domain.model.CityModel;
import com.pragma.home360.domain.model.DepartmentModel;
import com.pragma.home360.domain.ports.in.CityServicePort;
import com.pragma.home360.domain.ports.out.CityPersistencePort;
import com.pragma.home360.domain.ports.out.DepartmentPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CityUseCaseTest {

    @Mock
    private CityPersistencePort cityPersistencePort;

    @Mock
    private DepartmentPersistencePort departmentPersistencePort;

    @InjectMocks
    private CityUseCase cityUseCase;

    private CityModel validCity;
    private DepartmentModel department;

    @BeforeEach
    void setUp() {
        validCity = new CityModel();
        validCity.setName("Medellín");
        validCity.setDescription("Ciudad de la eterna primavera");

        department = new DepartmentModel(1L, "Antioquia", "Departamento de Antioquia");
        department.setName("Antioquia");
    }

    @Test
    void testSave_ThrowsExceptionWhenCityNameIsNull() {
        validCity.setName(null);

        assertThatThrownBy(() -> cityUseCase.save(validCity, department.getName()))
                .isInstanceOf(CityCannotBeNullException.class)
                .hasMessageContaining("El nombre de la ciudad no puede ser nulo");

        verify(departmentPersistencePort, never()).getByName(anyString());
        verify(cityPersistencePort, never()).save(any(CityModel.class));
    }

    @Test
    void testSave_ThrowsExceptionWhenCityNameIsBlank() {
        validCity.setName("   ");

        assertThatThrownBy(() -> cityUseCase.save(validCity, department.getName()))
                .isInstanceOf(CityCannotBeNullException.class)
                .hasMessageContaining("El nombre de la ciudad no puede ser nulo");

        verify(departmentPersistencePort, never()).getByName(anyString());
        verify(cityPersistencePort, never()).save(any(CityModel.class));
    }

    @Test
    void testSave_ThrowsExceptionWhenCityDescriptionIsNull() {
        validCity.setDescription(null);

        assertThatThrownBy(() -> cityUseCase.save(validCity, department.getName()))
                .isInstanceOf(CityCannotBeNullException.class)
                .hasMessageContaining("La descripcion de la ciudad no puede ser nula");

        verify(departmentPersistencePort, never()).getByName(anyString());
        verify(cityPersistencePort, never()).save(any(CityModel.class));
    }

    @Test
    void testSave_ThrowsExceptionWhenCityDescriptionIsBlank() {
        validCity.setDescription("  ");

        assertThatThrownBy(() -> cityUseCase.save(validCity, department.getName()))
                .isInstanceOf(CityCannotBeNullException.class)
                .hasMessageContaining("La descripcion de la ciudad no puede ser nula");

        verify(departmentPersistencePort, never()).getByName(anyString());
        verify(cityPersistencePort, never()).save(any(CityModel.class));
    }

    @Test
    void testSave_ThrowsExceptionWhenDepartmentNotFound() {
        // Simula que no se encuentra el departamento
        when(departmentPersistencePort.getByName(department.getName())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> cityUseCase.save(validCity, department.getName()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Departamento no encontrado");

        verify(departmentPersistencePort, times(1)).getByName(department.getName());
        verify(cityPersistencePort, never()).save(any(CityModel.class));
    }

    @Test
    void testSave_Successful() {
        // Simula que se encuentra el departamento
        when(departmentPersistencePort.getByName(department.getName())).thenReturn(Optional.of(department));

        cityUseCase.save(validCity, department.getName());

        // Verifica que se haya seteado el departamento en la ciudad
        assertThat(validCity.getDepartment()).isEqualTo(department);

        // Capturamos el parámetro enviado al método save del persistence port
        ArgumentCaptor<CityModel> cityCaptor = ArgumentCaptor.forClass(CityModel.class);
        verify(cityPersistencePort, times(1)).save(cityCaptor.capture());
        CityModel savedCity = cityCaptor.getValue();
        assertThat(savedCity.getName()).isEqualTo(validCity.getName());
        assertThat(savedCity.getDescription()).isEqualTo(validCity.getDescription());
        assertThat(savedCity.getDepartment()).isEqualTo(department);
    }

    @Test
    void testGetCities_ReturnsListOfCities() {
        List<CityModel> expectedCities = Collections.singletonList(validCity);
        when(cityPersistencePort.getCities(0, 10, true)).thenReturn(expectedCities);

        List<CityModel> actualCities = cityUseCase.getCities(0, 10, true);
        assertThat(actualCities).isEqualTo(expectedCities);
        verify(cityPersistencePort, times(1)).getCities(0, 10, true);
    }
}
