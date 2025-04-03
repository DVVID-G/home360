package com.pragma.home360.infrastructure.mappers;

import com.pragma.home360.domain.model.DeparmentModel;
import com.pragma.home360.infrastructure.entities.DeparmentEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeparmentEntityMapper {
    DeparmentEntity modelToEntity(DeparmentModel deparmentModel);
    DeparmentModel entityToModel(DeparmentEntity deparmentEntity);
    List<DeparmentModel> entityListToModelList(List<DeparmentEntity> deparmentEntities);
}