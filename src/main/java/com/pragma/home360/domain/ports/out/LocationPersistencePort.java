package com.pragma.home360.domain.ports.out;

import com.pragma.home360.domain.model.LocationModel;

import java.util.List;

public interface LocationPersistencePort {
    void save(LocationModel locationModel);
    List<LocationModel> getLocations(Integer page, Integer size, boolean orderAsc);
}
