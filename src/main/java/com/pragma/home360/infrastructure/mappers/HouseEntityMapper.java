package com.pragma.home360.infrastructure.mappers;

import com.pragma.home360.domain.model.HouseModel;
import com.pragma.home360.infrastructure.entities.HouseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HouseEntityMapper {
    @Mapping(target = "status", source = "publicationStatus")
    HouseEntity modelToEntity(HouseModel houseModel);
    @Mapping(target = "publicationStatus", source = "status")
    HouseModel entityToModel(HouseEntity houseEntity);
    List<HouseModel> entityListToModelList(List<HouseEntity> houseEntities);
}
