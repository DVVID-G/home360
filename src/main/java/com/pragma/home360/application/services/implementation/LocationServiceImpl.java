package com.pragma.home360.application.services.implementation;

import com.pragma.commons.configurations.utils.Constants;
import com.pragma.home360.application.dto.request.SaveLocationRequest;
import com.pragma.home360.application.dto.response.LocationResponse;
import com.pragma.home360.application.dto.response.SaveLocationResponse;
import com.pragma.home360.application.mappers.LocationDtoMapper;
import com.pragma.home360.application.services.LocationService;
import com.pragma.home360.domain.model.LocationModel;
import com.pragma.home360.domain.ports.in.LocationServicePort;
import com.pragma.home360.domain.usecases.LocationUseCase;
import com.pragma.home360.infrastructure.entities.LocationEntity;
import com.pragma.home360.infrastructure.repositories.mysql.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationServicePort locationServicePort;
    private final LocationDtoMapper locationDtoMapper;



    @Override
    public SaveLocationResponse save(SaveLocationRequest locationRequest) {
        locationServicePort.save(locationDtoMapper.requestToModel(locationRequest), locationRequest.cityName());
        return new SaveLocationResponse(Constants.SAVE_LOCATION_RESPONSE_MESSAGE, LocalDateTime.now());

    }

    @Override
    public Page<LocationResponse> getLocations(Integer page, Integer size, boolean orderAsc) {
        List<LocationModel> locations = locationServicePort.getLocations(page, size, orderAsc);
        List<LocationResponse> locationResponses = locationDtoMapper.modelListToResponseList(locations);
        return new PageImpl<>(locationResponses, PageRequest.of(page, size, Sort.by(orderAsc ? Sort.Direction.ASC : Sort.Direction.DESC, "id")), locations.size());

    }

    @Override
    public List<LocationResponse> searchLocations(String searchText) {
        return locationDtoMapper.modelListToResponseList(
                locationServicePort.searchLocations(searchText));
    }

}
