package com.pragma.home360.application.services.implementation;

import com.pragma.commons.configurations.utils.Constants;
import com.pragma.home360.application.dto.request.SaveLocationRequest;
import com.pragma.home360.application.dto.response.SaveLocationResponse;
import com.pragma.home360.application.mappers.LocationDtoMapper;
import com.pragma.home360.application.services.LocationService;
import com.pragma.home360.domain.model.LocationModel;
import com.pragma.home360.domain.ports.in.LocationServicePort;
import com.pragma.home360.domain.usecases.LocationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationServicePort locationServicePort;
    private final LocationDtoMapper locationDtoMapper;
    private final LocationUseCase locationUseCase;


    @Override
    public SaveLocationResponse save(SaveLocationRequest locationRequest) {
        var locationModel = locationDtoMapper.requestToModel(locationRequest);
        locationUseCase.save(locationModel, locationRequest.cityName());
        return new SaveLocationResponse(Constants.SAVE_CITY_RESPONSE_MESSAGE,
                LocalDateTime.now());
    }

    @Override
    public List<LocationModel> getLocations(Integer page, Integer size, boolean orderAsc) {
        return locationServicePort.getLocations(page, size, orderAsc);
    }


}
