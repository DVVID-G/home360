package com.pragma.home360.application.mappers;

import com.pragma.home360.application.dto.request.SaveDeparmentRequest;
import com.pragma.home360.application.dto.response.DeparmentResponse;
import com.pragma.home360.domain.model.DeparmentModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface DeparmentDtoMapper {
    DeparmentModel requestToModel(SaveDeparmentRequest saveDeparmentRequest);
    DeparmentResponse modelToResponse(DeparmentModel deparmentModel);
    List<DeparmentResponse> modelListToResponseList(List<DeparmentModel> deparments);
}
