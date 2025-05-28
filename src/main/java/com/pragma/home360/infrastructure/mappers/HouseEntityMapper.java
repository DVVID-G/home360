package com.pragma.home360.infrastructure.mappers;

import com.pragma.home360.domain.model.HouseModel;
import com.pragma.home360.infrastructure.entities.HouseEntity;

import java.util.List;

public interface HouseEntityMapper {
    HouseEntity modelToEntity(HouseModel houseModel);
    HouseModel entityToModel(HouseEntity houseEntity);
    List<HouseModel> entityListToModelList(List<HouseEntity> houseEntities);
}
