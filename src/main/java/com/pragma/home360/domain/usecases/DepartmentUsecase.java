package com.pragma.home360.domain.usecases;

import com.pragma.home360.domain.exceptions.DepartmentAlreadyExistsException;
import com.pragma.home360.domain.exceptions.DepartmentCannotBeNullException;
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

        if(departmentModel.getName() == null || departmentModel.getName().isBlank()) {
            throw new DepartmentCannotBeNullException("El departamento no puede ser nulo");
        }
        if(departmentModel.getDescription() == null || departmentModel.getDescription().isBlank()) {
            throw new DepartmentCannotBeNullException("La descripcion no puede ser nula");
        }

        if (departmentPersistencePort.existsByName(departmentModel.getName())) { //Validacion de unicidad
            throw new DepartmentAlreadyExistsException("El departamento '" + departmentModel.getName() + "' ya existe");
        }

        departmentPersistencePort.save(departmentModel);
    }
    @Override
    public List<DepartmentModel> getDepartments(Integer page, Integer size, boolean orderAsc) {
        return departmentPersistencePort.getDepartments(page, size, orderAsc);
    }

}
