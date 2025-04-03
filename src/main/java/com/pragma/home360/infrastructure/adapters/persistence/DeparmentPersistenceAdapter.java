package com.pragma.home360.infrastructure.adapters.persistence;

import com.pragma.home360.domain.model.DeparmentModel;

import com.pragma.home360.domain.ports.out.DeparmentPersistencePort;
import com.pragma.home360.infrastructure.mappers.DeparmentEntityMapper;
import com.pragma.home360.infrastructure.repositories.mysql.DeparmentRepository;
import lombok.RequiredArgsConstructor;
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
        return deparmentEntityMapper.entityListToModelList(deparmentRepository.findAll());
    }

}
