package com.pragma.home360.application.mappers;

import com.pragma.home360.application.dto.request.SaveHouseRequest;
import com.pragma.home360.application.dto.response.HouseResponse;
import com.pragma.home360.domain.model.HouseModel;
import com.pragma.home360.infrastructure.entities.HouseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HouseDtoMapper {
    HouseModel requestToModel(SaveHouseRequest saveHouseRequest);
    HouseResponse modelToResponse(HouseModel houseModel);
    List<HouseResponse> modelListToResponseList(List<HouseModel> houseModelList);
    HouseResponse entityToResponse(HouseEntity houseEntity);
}
