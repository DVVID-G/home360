package com.pragma.home360.infrastructure.mappers;

import com.pragma.home360.domain.model.LocationModel;
import com.pragma.home360.infrastructure.entities.LocationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = CityEntityMapper.class)
public interface LocationEntityMapper {
    @Mapping(target = "cityId", source = "cityId")
    LocationEntity modelToEntity(LocationModel locationModel);
    @Mapping(target = "city", ignore = true) // Se cargará bajo demanda
    @Mapping(target = "cityId", source = "cityId")
    LocationModel entityToModel(LocationEntity locationEntity);

    List<LocationModel> entityListToModelList(List<LocationEntity> locationEntities);
}
