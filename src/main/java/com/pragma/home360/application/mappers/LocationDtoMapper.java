package com.pragma.home360.application.mappers;

import com.pragma.home360.application.dto.request.SaveLocationRequest;
import com.pragma.home360.application.dto.response.LocationResponse;
import com.pragma.home360.domain.model.LocationModel;
import com.pragma.home360.infrastructure.entities.LocationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocationDtoMapper {
    @Mapping(target = "city.name", source = "cityName")
    LocationModel requestToModel(SaveLocationRequest saveLocationRequest);
    LocationResponse modelToResponse(LocationModel locationModel);
    LocationResponse entityToResponse(LocationEntity locationEntity);

}
