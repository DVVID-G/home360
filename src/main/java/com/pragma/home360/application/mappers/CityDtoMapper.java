package com.pragma.home360.application.mappers;

import com.pragma.home360.application.dto.request.SaveCityRequest;
import com.pragma.home360.application.dto.response.CityResponse;
import com.pragma.home360.domain.model.CityModel;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CityDtoMapper {
    @Mapping(target = "name", source = "cityName")
    @Mapping(target = "description", source = "cityDescription")
    CityModel requestToModel(SaveCityRequest saveCityRequest);
    @Mapping(target = "cityName", source = "name")
    @Mapping(target = "cityDescription", source = "description")
    @Mapping(target = "departmentModel", source = "department")

    CityResponse modelToResponse(CityModel cityModel);

    List<CityResponse> modelListToResponseList(List<CityModel> cities);
}