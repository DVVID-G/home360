package com.pragma.home360.domain.usecases;




import com.pragma.home360.domain.model.CityModel;
import com.pragma.home360.domain.model.DeparmentModel;
import com.pragma.home360.domain.model.LocationModel;
import com.pragma.home360.domain.ports.in.LocationServicePort;
import com.pragma.home360.domain.ports.out.CityPersistencePort;
import com.pragma.home360.domain.ports.out.LocationPersistencePort;
import com.pragma.home360.infrastructure.entities.CityEntity;
import com.pragma.home360.infrastructure.mappers.CityEntityMapper;
import com.pragma.home360.infrastructure.repositories.mysql.CityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;


public class LocationUseCase implements LocationServicePort {
    private final LocationPersistencePort locationPersistencePort;
    private final CityPersistencePort cityPersistencePort;

    public LocationUseCase(LocationPersistencePort locationPersistencePort,
                           CityPersistencePort cityPersistencePort) {
        this.locationPersistencePort = locationPersistencePort;
        this.cityPersistencePort = cityPersistencePort;
    }

    @Override
    public void save(LocationModel locationModel, String cityName) {
        CityModel cityModel= cityPersistencePort.getByName(cityName);
        locationModel.setCityId(cityModel.getId());
        locationPersistencePort.save(locationModel);
    }

    @Override
    public List<LocationModel> getLocations(Integer page, Integer size, boolean orderAsc) {
        return locationPersistencePort.getLocations(page, size, orderAsc);
    }

}
