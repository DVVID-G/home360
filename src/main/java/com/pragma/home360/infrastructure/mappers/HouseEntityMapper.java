package com.pragma.home360.infrastructure.mappers;

import com.pragma.home360.domain.model.HouseModel;
import com.pragma.home360.infrastructure.entities.HouseEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HouseEntityMapper {
    HouseEntity modelToEntity(HouseModel houseModel);
    HouseModel entityToModel(HouseEntity houseEntity);
    List<HouseModel> entityListToModelList(List<HouseEntity> houseEntities);
}
