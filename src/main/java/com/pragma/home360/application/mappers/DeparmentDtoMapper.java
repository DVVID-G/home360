package com.pragma.home360.application.mappers;

import com.pragma.home360.application.dto.request.SaveDeparmentRequest;
import com.pragma.home360.application.dto.response.DeparmentResponse;
import com.pragma.home360.domain.model.DeparmentModel;
import com.pragma.home360.infrastructure.entities.DeparmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface DeparmentDtoMapper {
    @Mapping(source = "deparmentName", target = "name")
    @Mapping(source = "deparmentDescription", target = "description")
    DeparmentModel requestToModel(SaveDeparmentRequest saveDeparmentRequest);

    // Mapeo del modelo a la respuesta: convierte "name" a "cityName" y "description" a "cityDescription"
    @Mapping(source = "name", target = "deparmentName")
    @Mapping(source = "description", target = "deparmentDescription")
    DeparmentResponse requestToResponse(DeparmentModel deparmentModel);

    List<DeparmentResponse> modelListToResponseList(List<DeparmentModel> deparments);

    // Si usas este metodo para mapear directamente de entidad a DTO, también debes configurar el mapeo
    @Mapping(source = "name", target = "deparmentName")
    @Mapping(source = "description", target = "deparmentDescription")
    DeparmentResponse entityToResponse(DeparmentEntity deparmentEntity);
}