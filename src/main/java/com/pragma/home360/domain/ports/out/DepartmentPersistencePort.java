package com.pragma.home360.domain.ports.out;

import com.pragma.home360.domain.model.DepartmentModel;

import java.util.List;
import java.util.Optional;

public interface DepartmentPersistencePort {
    void save(DepartmentModel departmentModel);
    List<DepartmentModel> getDepartments(Integer page, Integer size, boolean orderAsc);
    Optional<DepartmentModel> getByName(String departmentName);
    boolean existsByName(String departmentName);
}
