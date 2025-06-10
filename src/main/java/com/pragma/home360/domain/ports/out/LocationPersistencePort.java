package com.pragma.home360.domain.ports.out;

import com.pragma.home360.domain.model.LocationModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LocationPersistencePort {
    void save(LocationModel locationModel);
    LocationModel getLocationByName(String locationName);
    List<LocationModel> getLocations(Integer page, Integer size, boolean orderAsc);
    List <LocationModel> searchLocations(String searchText);
}
