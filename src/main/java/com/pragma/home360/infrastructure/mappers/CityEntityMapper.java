package com.pragma.home360.infrastructure.mappers;

import com.pragma.home360.domain.model.CityModel;
import com.pragma.home360.infrastructure.entities.CityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = DeparmentEntityMapper.class)
public interface CityEntityMapper {
    CityEntity modelToEntity(CityModel cityModel);
    CityModel entityToModel(CityEntity cityEntity);

    List<CityModel> entityListToModelList(List<CityEntity> cityEntities);

    List<CityEntity> modelListToEntityList(List<CityModel> cityModels);
}
