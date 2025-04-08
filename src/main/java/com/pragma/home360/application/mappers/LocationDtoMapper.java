package com.pragma.home360.application.mappers;


import com.pragma.home360.application.dto.request.SaveLocationRequest;
import com.pragma.home360.application.dto.response.LocationResponse;
import com.pragma.home360.domain.model.LocationModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LocationDtoMapper {
    @Mapping(target = "barrio", source = "barrio")

    LocationModel requestToModel(SaveLocationRequest saveLocationRequest);
    @Mapping(target = "barrio", source = "barrio")

    LocationResponse requestToResponse(LocationModel locationModel);
    @Mapping(target = "barrio", source = "barrio")
    List<LocationResponse> modelListToResponseList(List<LocationModel> locations);
}
