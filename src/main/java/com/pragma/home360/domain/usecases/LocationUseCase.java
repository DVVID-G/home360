package com.pragma.home360.domain.usecases;




import com.pragma.home360.domain.model.CityModel;
import com.pragma.home360.domain.model.LocationModel;
import com.pragma.home360.domain.ports.in.LocationServicePort;
import com.pragma.home360.domain.ports.out.CityPersistencePort;
import com.pragma.home360.domain.ports.out.LocationPersistencePort;


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
        locationModel.setCity(cityModel);
        locationPersistencePort.save(locationModel);
    }

    @Override
    public List<LocationModel> getLocations(Integer page, Integer size, boolean orderAsc) {
        return locationPersistencePort.getLocations(page, size, orderAsc);
    }

    @Override
    public List<LocationModel> searchLocations(String searchText) {
        return locationPersistencePort.searchLocations(searchText);
    }


}
