package com.pragma.home360.infrastructure.adapters.persistence;


import com.pragma.commons.configurations.utils.Constants;
import com.pragma.home360.domain.model.CityModel;
import com.pragma.home360.domain.ports.out.CityPersistencePort;
import com.pragma.home360.infrastructure.mappers.CityEntityMapper;
import com.pragma.home360.infrastructure.repositories.mysql.CityRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CityPersistenceAdapter implements CityPersistencePort {
    private final CityRepository cityRepository;
    private final CityEntityMapper cityEntityMapper;

    @Override
    public void save(CityModel cityModel) {
        cityRepository.save(cityEntityMapper.modelToEntity(cityModel));
    }

    @Override
    public List<CityModel> getCities(Integer page, Integer size, boolean orderAsc) {
        Pageable pagination;
        if (orderAsc) pagination = PageRequest.of(page, size, Sort.by(Constants.PAGEABLE_FIELD_NAME).ascending());
        else pagination = PageRequest.of(page, size, Sort.by(Constants.PAGEABLE_FIELD_NAME).descending());
        return cityEntityMapper.entityListToModelList(cityRepository.findAll(pagination).getContent());
    }

}
