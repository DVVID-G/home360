package com.pragma.home360.domain.usecases;




import com.pragma.home360.domain.model.LocationModel;
import com.pragma.home360.domain.ports.in.LocationServicePort;
import com.pragma.home360.domain.ports.out.LocationPersistencePort;
import com.pragma.home360.infrastructure.entities.CityEntity;
import com.pragma.home360.infrastructure.mappers.CityEntityMapper;
import com.pragma.home360.infrastructure.repositories.mysql.CityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationUseCase implements LocationServicePort {
    private final LocationPersistencePort locationPersistencePort;
    private final CityRepository cityRepository;
    private final CityEntityMapper cityEntityMapper;

    @Override
    public void save(LocationModel locationModel) {
        /*String cityName = locationModel.getCity().getName();

        CityEntity cityEntity = cityRepository.findByNameWithDepartment(cityName)
                .orElseThrow(() -> new EntityNotFoundException("Ciudad no encontrada: " + cityName));

        locationModel.setCity(cityEntityMapper.entityToModel(cityEntity));*/
        locationPersistencePort.save(locationModel);
    }

    @Override
    public List<LocationModel> getLocations(Integer page, Integer size, boolean orderAsc) {
        return locationPersistencePort.getLocations(page, size, orderAsc);
    }

}
