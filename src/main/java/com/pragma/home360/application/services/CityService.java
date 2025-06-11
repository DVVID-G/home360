package com.pragma.home360.application.services;

import com.pragma.home360.application.dto.request.SaveCityRequest;
import com.pragma.home360.application.dto.response.CityResponse;
import com.pragma.home360.application.dto.response.SaveCityResponse;

import java.util.List;

public interface CityService {
    SaveCityResponse save(SaveCityRequest cityRequest);
    List<CityResponse> getCities(Integer page, Integer size, boolean orderAsc);
}
