package com.pragma.home360.application.services.implementation;

import com.pragma.commons.configurations.utils.Constants;
import com.pragma.home360.application.dto.request.SaveDepartmentRequest;
import com.pragma.home360.application.dto.response.DepartmentResponse;
import com.pragma.home360.application.dto.response.SaveDepartmentResponse;
import com.pragma.home360.application.mappers.DepartmentDtoMapper;
import com.pragma.home360.application.services.DepartmentService;
import com.pragma.home360.domain.ports.in.DepartmentServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentServicePort departmentServicePort;
    private final DepartmentDtoMapper departmentDtoMapper;

    @Override
    public SaveDepartmentResponse save(SaveDepartmentRequest request) {
        departmentServicePort.save(departmentDtoMapper.requestToModel(request));
        return new SaveDepartmentResponse(Constants.SAVE_DEPARMENT_RESPONSE_MESSAGE, LocalDateTime.now());
    }

    @Override
    public List<DepartmentResponse> getDepartments(Integer page, Integer size, boolean orderAsc) {
        return departmentDtoMapper.modelListToResponseList(departmentServicePort.getDepartments(page, size, orderAsc));
    }

}
