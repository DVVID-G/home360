package com.pragma.home360.domain.usecases;

import com.pragma.home360.domain.model.CityModel;
import com.pragma.home360.domain.model.DepartmentModel;
import com.pragma.home360.domain.model.LocationModel;
import com.pragma.home360.domain.ports.out.CityPersistencePort;
import com.pragma.home360.domain.ports.out.LocationPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocationUseCaseTest {

    @Mock
    private LocationPersistencePort locationPersistencePort;

    @Mock
    private CityPersistencePort cityPersistencePort;

    @InjectMocks
    private LocationUseCase locationUseCase;

    private LocationModel locationModel;
    private CityModel cityModel;

    @BeforeEach
    void setUp() {
        locationModel = new LocationModel(1L,new CityModel(1L, "cityName","cityDescription" ,new DepartmentModel(1L, "Valle del Cauca", "Departamento del Valle del Cauca")),"barrio");
        cityModel = new CityModel();
        cityModel.setName("TestCity");
    }

    @Test
    void testSave_Successful() {
        String cityName = "TestCity";
        // Configuramos el mock para que retorne el CityModel cuando se busque por nombre
        when(cityPersistencePort.getByName(cityName)).thenReturn(cityModel);

        // Ejecutamos el método save
        locationUseCase.save(locationModel, cityName);

        // Verificamos que se haya obtenido la ciudad y se haya asignado correctamente al locationModel
        assertThat(locationModel.getCity()).isEqualTo(cityModel);

        // Capturamos el argumento enviado al método save del persistence port
        ArgumentCaptor<LocationModel> locationCaptor = ArgumentCaptor.forClass(LocationModel.class);
        verify(locationPersistencePort, times(1)).save(locationCaptor.capture());
        LocationModel savedLocation = locationCaptor.getValue();

        // Verificamos que el locationModel enviado en el guardado es el mismo que se configuró
        assertThat(savedLocation).isEqualTo(locationModel);
    }

    @Test
    void testGetLocations_ReturnsListOfLocations() {
        // Creamos una lista esperada de LocationModel
        List<LocationModel> expectedLocations = Collections.singletonList(locationModel);

        // Configuramos el mock para retornar la lista esperada
        when(locationPersistencePort.getLocations(0, 10, true)).thenReturn(expectedLocations);

        // Ejecutamos el método getLocations
        List<LocationModel> actualLocations = locationUseCase.getLocations(0, 10, true);

        // Verificamos que la lista retornada es la esperada
        assertThat(actualLocations).isEqualTo(expectedLocations);
        verify(locationPersistencePort, times(1)).getLocations(0, 10, true);
    }
}
