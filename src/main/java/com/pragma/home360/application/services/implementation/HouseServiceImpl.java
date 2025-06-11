package com.pragma.home360.application.services.implementation;

import com.pragma.commons.configurations.utils.Constants;
import com.pragma.home360.application.dto.request.HouseSearchRequest;
import com.pragma.home360.application.dto.request.SaveHouseRequest;
import com.pragma.home360.application.dto.response.HouseResponse;
import com.pragma.home360.application.dto.response.SaveHouseResponse;
import com.pragma.home360.application.mappers.HouseDtoMapper;
import com.pragma.home360.application.services.HouseService;
import com.pragma.home360.domain.model.HouseModel;
import com.pragma.home360.domain.model.HouseSearchFilters;
import com.pragma.home360.domain.model.PaginatedResult;
import com.pragma.home360.domain.ports.in.HouseServicePort;
import com.pragma.home360.infrastructure.entities.HouseEntity;
import com.pragma.home360.infrastructure.repositories.mysql.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {
    private final HouseServicePort houseServicePort;
    private final HouseDtoMapper houseDtoMapper;
    private final HouseRepository houseRepository;

    @Override
    public SaveHouseResponse save(SaveHouseRequest houseRequest) {
        HouseModel houseModel = houseDtoMapper.requestToModel(houseRequest);
        houseServicePort.save(houseModel, houseRequest.categoryName(), houseRequest.locationName(), houseRequest.status());
        return new SaveHouseResponse(Constants.SAVE_HOUSE_RESPONSE_MESSAGE, LocalDateTime.now());
    }

    @Override
    public List<HouseResponse> getHouses(Integer page, Integer size, boolean orderAsc) {
        Sort sort = Sort.by(orderAsc ? Sort.Direction.ASC : Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<HouseEntity> pageEntities = houseRepository.findAll(pageable);
        return pageEntities.map(houseDtoMapper::entityToResponse).getContent();
    }
    @Override
    public Page<HouseResponse> searchHouses(Integer page, Integer size, HouseSearchRequest searchRequest) {
        // ✅ Convertir DTO a modelo de dominio
        HouseSearchFilters filters = new HouseSearchFilters(
                searchRequest.location(), searchRequest.category(),
                searchRequest.minRooms(), searchRequest.maxRooms(),
                searchRequest.minBathrooms(), searchRequest.maxBathrooms(),
                searchRequest.minPrice(), searchRequest.maxPrice(),
                searchRequest.sortBy(), searchRequest.sortDirection()
        );

        // ✅ Usar dominio
        PaginatedResult<HouseModel> result = houseServicePort.getHousesWithFilters(page, size, filters);

        // ✅ Convertir resultado de dominio a Spring Page (para el Controller)
        List<HouseResponse> responses = houseDtoMapper.modelListToResponseList(result.getContent());

        return new PageImpl<>(
                responses,
                PageRequest.of(page, size),
                result.getTotalElements()
        );


    }}



