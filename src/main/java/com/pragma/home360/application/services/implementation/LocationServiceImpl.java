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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private final LocationRepository locationRepository;


    @Override
    public SaveLocationResponse save(SaveLocationRequest locationRequest) {
        var locationModel = locationDtoMapper.requestToModel(locationRequest);
        locationUseCase.save(locationModel, locationRequest.cityName());
        return new SaveLocationResponse(Constants.SAVE_CITY_RESPONSE_MESSAGE,
                LocalDateTime.now());
    }

    @Override
    public Page<LocationResponse> getLocations(Integer page, Integer size, boolean orderAsc) {
        Sort sort = Sort.by(orderAsc ? Sort.Direction.ASC : Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<LocationEntity> pageEntities = locationRepository.findAll(pageable);
        return pageEntities.map(locationDtoMapper::entityToResponse);
    }

    @Override
    public List<LocationResponse> searchLocations(String searchText) {
        return locationDtoMapper.modelListToResponseList(locationUseCase.searchLocations(searchText));
    }

}
