package com.pragma.home360.infrastructure.mappers;

import com.pragma.home360.domain.model.CityModel;
import com.pragma.home360.domain.model.DeparmentModel;
import com.pragma.home360.infrastructure.entities.CityEntity;
import com.pragma.home360.infrastructure.entities.DeparmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeparmentEntityMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    DeparmentEntity modelToEntity(DeparmentModel deparmentModel);
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    DeparmentModel entityToModel(DeparmentEntity deparmentEntity);
    List<DeparmentModel> entityListToModelList(List<DeparmentEntity> deparmentEntities);
}
