package com.pragma.home360.application.mappers;

import com.pragma.home360.application.dto.request.SaveDepartmentRequest;
import com.pragma.home360.application.dto.response.DepartmentResponse;
import com.pragma.home360.domain.model.DepartmentModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface DepartmentDtoMapper {
    @Mapping(source = "departmentName", target = "name")
    @Mapping(source = "departmentDescription", target = "description")
    DepartmentModel requestToModel(SaveDepartmentRequest saveDepartmentRequest);

    // Mapeo del modelo a la respuesta: convierte "name" a "cityName" y "description" a "cityDescription"
    @Mapping(source = "name", target = "departmentName")
    @Mapping(source = "description", target = "departmentDescription")
    DepartmentResponse requestToResponse(DepartmentModel departmentModel);

    List<DepartmentResponse> modelListToResponseList(List<DepartmentModel> deparments);


}