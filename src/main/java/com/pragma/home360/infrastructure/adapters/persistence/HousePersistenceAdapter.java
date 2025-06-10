package com.pragma.home360.infrastructure.adapters.persistence;

import com.pragma.commons.configurations.utils.Constants;
import com.pragma.home360.domain.model.HouseModel;
import com.pragma.home360.domain.ports.out.HousePersistencePort;
import com.pragma.home360.infrastructure.mappers.HouseEntityMapper;
import com.pragma.home360.infrastructure.repositories.mysql.HouseRepository;
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
public class HousePersistenceAdapter implements HousePersistencePort {
    private final HouseRepository houseRepository;
    private final HouseEntityMapper houseEntityMapper;

    @Override
    public void save(HouseModel houseModel) {
        houseRepository.save(houseEntityMapper.modelToEntity(houseModel));
    }

    @Override
    public List<HouseModel> getHouses(Integer page, Integer size, boolean orderAsc) {
        Pageable pagination;
        if (orderAsc) pagination = PageRequest.of(page, size, Sort.by(Constants.PAGEABLE_FIELD_NAME).ascending());
        else pagination = PageRequest.of(page, size, Sort.by(Constants.PAGEABLE_FIELD_NAME).descending());
        return houseEntityMapper.entityListToModelList(houseRepository.findAll(pagination).getContent());
    }
}
