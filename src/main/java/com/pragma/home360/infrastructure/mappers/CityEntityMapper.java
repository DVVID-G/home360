package com.pragma.home360.infrastructure.mappers;

import com.pragma.home360.domain.model.CityModel;
import com.pragma.home360.infrastructure.entities.CityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper(componentModel = "spring")
public interface CityEntityMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    CityEntity modelToEntity(CityModel cityModel);
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    CityModel entityToModel(CityEntity cityEntity);
    List<CityModel> entityListToModelList(List<CityEntity> cityEntities);
}
