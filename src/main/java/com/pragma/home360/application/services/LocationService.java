package com.pragma.home360.application.services;

import com.pragma.home360.application.dto.request.SaveLocationRequest;
import com.pragma.home360.application.dto.response.LocationResponse;
import com.pragma.home360.application.dto.response.SaveLocationResponse;
import com.pragma.home360.domain.model.LocationModel;

import java.util.List;

public interface LocationService {
    SaveLocationResponse save(SaveLocationRequest request);
    List<LocationModel> getLocations(Integer page, Integer size, boolean orderAsc);
    List<LocationResponse> searchLocations(String searchText);

}
