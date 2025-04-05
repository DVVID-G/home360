package com.pragma.home360.application.services.implementation;


import com.pragma.commons.configurations.utils.Constants;
import com.pragma.home360.application.dto.request.SaveCityRequest;
import com.pragma.home360.application.dto.response.CityResponse;
import com.pragma.home360.application.dto.response.SaveCityResponse;
import com.pragma.home360.application.mappers.CityDtoMapper;
import com.pragma.home360.application.services.CityService;
import com.pragma.home360.domain.ports.in.CityServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityServicePort cityServicePort;
    private final CityDtoMapper cityDtoMapper;


    @Override
    public SaveCityResponse save(SaveCityRequest cityRequest) {
        cityServicePort.save(cityDtoMapper.requestToModel(cityRequest));
        return new SaveCityResponse(Constants.SAVE_CITY_RESPONSE_MESSAGE, LocalDateTime.now());
    }


    @Override
    public List<CityResponse> getCities(Integer page, Integer size, boolean orderAsc) {
        return cityDtoMapper.modelListToResponseList(cityServicePort.getCities(page, size, orderAsc));
    }
}
