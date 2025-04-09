package com.pragma.home360.domain.ports.in;

import com.pragma.home360.domain.model.LocationModel;

import java.util.List;

public interface LocationServicePort {
    void save (LocationModel locationModel, String cityName);
    List<LocationModel> getLocations(Integer page, Integer size, boolean orderAsc);
}
