package com.pragma.home360.infrastructure.adapters.persistence;


import com.pragma.home360.domain.model.LocationModel;
import com.pragma.home360.domain.ports.out.LocationPersistencePort;
import com.pragma.home360.infrastructure.mappers.LocationEntityMapper;
import com.pragma.home360.infrastructure.repositories.mysql.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LocationPersistenceAdapter implements LocationPersistencePort {
    private final LocationRepository locationRepository;
    private final LocationEntityMapper locationEntityMapper;


    @Override
    public void save(LocationModel locationModel) {
        locationRepository.save(locationEntityMapper.modelToEntity(locationModel));
    }

    @Override
    public LocationModel getLocationByName(String locationName) {
        return locationRepository.findByBarrio(locationName)
                .map(locationEntityMapper::entityToModel)
                .orElseThrow(() -> new RuntimeException("Location not found with name: " + locationName));
    }

    @Override
    public List<LocationModel> getLocations(Integer page, Integer size, boolean orderAsc) {
        Pageable pagination = PageRequest.of(page, size, Sort.by("id").ascending());
        return locationRepository.findAllWithCity(pagination)
                .stream()
                .map(locationEntityMapper::entityToModel)
                .toList();
    }

    @Override
    public List<LocationModel> searchLocations(String searchText) {
        return locationRepository.searchLocations(searchText)
                .stream()
                .map(locationEntityMapper::entityToModel)
                .toList();
    }


}


