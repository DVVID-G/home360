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
    @Mapping(source = "cityName", target = "name")
    @Mapping(source = "cityDescription", target = "description")
    CityModel requestToModel(SaveCityRequest saveCityRequest);

    // Corregir los nombres de las propiedades para CityResponse
    @Mapping(source = "name", target = "cityName")
    @Mapping(source = "description", target = "cityDescription")
    @Mapping(source = "department", target = "departmentModel")
    CityResponse modelToResponse(CityModel cityModel);

    @Mapping(source = "name", target = "cityName")
    @Mapping(source = "description", target = "cityDescription")
    List<CityResponse> modelListToResponseList(List<CityModel> cities);
}