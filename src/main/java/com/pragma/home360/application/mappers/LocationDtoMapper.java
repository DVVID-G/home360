package com.pragma.home360.application.mappers;

import com.pragma.home360.application.dto.request.SaveLocationRequest;
import com.pragma.home360.application.dto.response.LocationResponse;
import com.pragma.home360.domain.model.LocationModel;
import com.pragma.home360.infrastructure.entities.LocationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface LocationDtoMapper {
    @Mapping(target = "city.name", source = "cityName")
    LocationModel requestToModel(SaveLocationRequest saveLocationRequest);
    LocationResponse modelToResponse(LocationModel locationModel);
    List<LocationResponse> modelListToResponseList(List<LocationModel> locations);
    LocationResponse entityToResponse(LocationEntity locationEntity);

}
