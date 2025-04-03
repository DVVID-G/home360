package com.pragma.home360.infrastructure.mappers;

import com.pragma.home360.domain.model.LocationModel;
import com.pragma.home360.infrastructure.entities.LocationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = CityEntityMapper.class)
public interface LocationEntityMapper {
    LocationEntity modelToEntity(LocationModel locationModel);
    LocationModel entityToModel(LocationEntity locationEntity);
    List<LocationModel> entityListToModelList(List<LocationEntity> locationEntities);
}
