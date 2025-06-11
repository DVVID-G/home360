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
    @Mapping(target = "name", source = "departmentName")
    @Mapping(target = "description", source = "departmentDescription")

    DepartmentModel requestToModel(SaveDepartmentRequest saveDepartmentRequest);
    @Mapping(target = "departmentName", source = "name")
    @Mapping(target = "departmentDescription", source = "description")
    DepartmentResponse requestToResponse(DepartmentModel departmentModel);

    List<DepartmentResponse> modelListToResponseList(List<DepartmentModel> deparments);


}