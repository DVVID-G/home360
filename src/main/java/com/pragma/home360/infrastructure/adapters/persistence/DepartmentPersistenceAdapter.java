package com.pragma.home360.infrastructure.adapters.persistence;

import com.pragma.commons.configurations.utils.Constants;
import com.pragma.home360.domain.model.DepartmentModel;

import com.pragma.home360.domain.ports.out.DepartmentPersistencePort;
import com.pragma.home360.infrastructure.mappers.DepartmentEntityMapper;
import com.pragma.home360.infrastructure.repositories.mysql.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentPersistenceAdapter implements DepartmentPersistencePort {
    private final DepartmentRepository departmentRepository;
    private final DepartmentEntityMapper departmentEntityMapper;


    @Override
    public void save(DepartmentModel departmentModel) {
        departmentRepository.save(departmentEntityMapper.modelToEntity(departmentModel));
    }

    @Override
    public List<DepartmentModel> getDepartments(Integer page, Integer size, boolean orderAsc) {
        Pageable pagination;
        if (orderAsc) pagination = PageRequest.of(page, size, Sort.by(Constants.PAGEABLE_FIELD_NAME).ascending());
        else pagination = PageRequest.of(page, size, Sort.by(Constants.PAGEABLE_FIELD_NAME).descending());
        return departmentEntityMapper.entityListToModelList(departmentRepository.findAll(pagination).getContent());
    }

    @Override
    public Optional<DepartmentModel> getByName(String name) {
        return departmentRepository.findByName(name)
                .map(departmentEntityMapper::entityToModel);
    }

    @Override
    public boolean existsByName(String name) {
        return departmentRepository.existsByName(name);
    }


}
