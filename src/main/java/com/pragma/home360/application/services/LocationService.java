package com.pragma.home360.application.services;

import com.pragma.home360.application.dto.request.SaveLocationRequest;
import com.pragma.home360.application.dto.response.LocationResponse;
import com.pragma.home360.application.dto.response.SaveLocationResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LocationService {
    SaveLocationResponse save(SaveLocationRequest request);
    Page<LocationResponse> getLocations(Integer page, Integer size, boolean orderAsc);
    List<LocationResponse> searchLocations(String searchText);

}
