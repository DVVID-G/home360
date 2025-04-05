package com.pragma.home360.infrastructure.adapters.persistence;

import com.pragma.commons.configurations.utils.Constants;
import com.pragma.home360.domain.model.CityModel;
import com.pragma.home360.domain.model.DeparmentModel;

import com.pragma.home360.domain.ports.out.DeparmentPersistencePort;
import com.pragma.home360.infrastructure.mappers.DeparmentEntityMapper;
import com.pragma.home360.infrastructure.repositories.mysql.DeparmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DeparmentPersistenceAdapter implements DeparmentPersistencePort {
    private final DeparmentRepository deparmentRepository;
    private final DeparmentEntityMapper deparmentEntityMapper;


    @Override
    public void save(DeparmentModel deparmentModel) {
        deparmentRepository.save(deparmentEntityMapper.modelToEntity(deparmentModel));
    }

    @Override
    public List<DeparmentModel> getDeparments(Integer page, Integer size, boolean orderAsc) {
        Pageable pagination;
        if (orderAsc) pagination = PageRequest.of(page, size, Sort.by(Constants.PAGEABLE_FIELD_NAME).ascending());
        else pagination = PageRequest.of(page, size, Sort.by(Constants.PAGEABLE_FIELD_NAME).descending());
        return deparmentEntityMapper.entityListToModelList(deparmentRepository.findAll(pagination).getContent());
    }

    @Override
    public DeparmentModel getByName(String name) {
        return deparmentRepository.findByName(name)
                .map(deparmentEntityMapper::entityToModel)
                .orElse(null); // o lanza una excepción si prefieres
    }

}
