package com.pragma.home360.infrastructure.mappers;

import com.pragma.home360.domain.model.DepartmentModel;
import com.pragma.home360.infrastructure.entities.DepartmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentEntityMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    DepartmentEntity modelToEntity(DepartmentModel departmentModel);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    DepartmentModel entityToModel(DepartmentEntity departmentEntity);
    List<DepartmentModel> entityListToModelList(List<DepartmentEntity> departmentEntities);
}
