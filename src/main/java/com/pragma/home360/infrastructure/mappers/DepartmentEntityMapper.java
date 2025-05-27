package com.pragma.home360.infrastructure.mappers;

import com.pragma.home360.domain.model.DepartmentModel;
import com.pragma.home360.infrastructure.entities.DepartmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentEntityMapper {
    DepartmentEntity modelToEntity(DepartmentModel departmentModel);
    DepartmentModel entityToModel(DepartmentEntity departmentEntity);
    List<DepartmentModel> entityListToModelList(List<DepartmentEntity> departmentEntities);
}
