package com.pragma.home360.domain.ports.in;

import com.pragma.home360.domain.model.DepartmentModel;

import java.util.List;

public interface DepartmentServicePort {
    void save(DepartmentModel departmentModel);
    List<DepartmentModel> getDepartments(Integer page, Integer size, boolean orderAsc);
}
