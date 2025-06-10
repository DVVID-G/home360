package com.pragma.home360.application.services.implementation;

import com.pragma.commons.configurations.utils.Constants;
import com.pragma.home360.application.dto.request.SaveHouseRequest;
import com.pragma.home360.application.dto.response.HouseResponse;
import com.pragma.home360.application.dto.response.SaveHouseResponse;
import com.pragma.home360.application.mappers.HouseDtoMapper;
import com.pragma.home360.application.services.HouseService;
import com.pragma.home360.domain.model.HouseModel;
import com.pragma.home360.domain.ports.in.HouseServicePort;
import com.pragma.home360.infrastructure.entities.HouseEntity;
import com.pragma.home360.infrastructure.repositories.mysql.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        houseServicePort.save(houseDtoMapper.requestToModel(houseRequest));
        return new SaveHouseResponse(Constants.SAVE_HOUSE_RESPONSE_MESSAGE, LocalDateTime.now());
    }

    @Override
    public List<HouseResponse> getHouses(Integer page, Integer size, boolean orderAsc) {
        Sort sort = Sort.by(orderAsc ? Sort.Direction.ASC : Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<HouseEntity> pageEntities = houseRepository.findAll(pageable);
        return pageEntities.map(houseDtoMapper::entityToResponse).getContent();
    }


}



