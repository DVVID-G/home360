package com.pragma.home360.application.mappers;

import com.pragma.home360.application.dto.request.SaveCityRequest;
import com.pragma.home360.application.dto.response.CityResponse;
import com.pragma.home360.domain.model.CityModel;
import com.pragma.home360.infrastructure.entities.CityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CityDtoMapper {
    @Mapping(source = "cityName", target = "name")
    @Mapping(source = "cityDescription", target = "description")
    CityModel requestToModel(SaveCityRequest saveCityRequest);

    // Mapeo del modelo a la respuesta: convierte "name" a "cityName" y "description" a "cityDescription"
    @Mapping(source = "name", target = "cityName")
    @Mapping(source = "description", target = "cityDescription")
    CityResponse requestToResponse(CityModel cityModel);
    @Mapping(source = "name", target = "cityName")
    @Mapping(source = "description", target = "cityDescription")
    List<CityResponse> modelListToResponseList(List<CityModel> cities);

}