package com.pragma.home360.application.services;

import com.pragma.home360.application.dto.request.SaveDepartmentRequest;
import com.pragma.home360.application.dto.response.DepartmentResponse;
import com.pragma.home360.application.dto.response.SaveDepartmentResponse;

import java.util.List;

public interface DepartmentService {
    SaveDepartmentResponse save(SaveDepartmentRequest request);
    List<DepartmentResponse> getDepartments(Integer page, Integer size, boolean orderAsc);
}
