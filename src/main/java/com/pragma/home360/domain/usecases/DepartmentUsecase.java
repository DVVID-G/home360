package com.pragma.home360.domain.usecases;

import com.pragma.home360.domain.model.DepartmentModel;
import com.pragma.home360.domain.ports.in.DepartmentServicePort;
import com.pragma.home360.domain.ports.out.DepartmentPersistencePort;

import java.util.List;


public class DepartmentUsecase implements DepartmentServicePort {
    private final DepartmentPersistencePort departmentPersistencePort;

    public DepartmentUsecase(DepartmentPersistencePort departmentPersistencePort) {
        this.departmentPersistencePort = departmentPersistencePort;
    }

    @Override
    public void save(DepartmentModel departmentModel) {

        departmentPersistencePort.save(departmentModel);
    }
    @Override
    public List<DepartmentModel> getDepartments(Integer page, Integer size, boolean orderAsc) {
        return departmentPersistencePort.getDepartments(page, size, orderAsc);
    }

}
