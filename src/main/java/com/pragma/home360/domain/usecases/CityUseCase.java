package com.pragma.home360.domain.usecases;

import com.pragma.home360.domain.exceptions.CityCannotBeNullException;
import com.pragma.home360.domain.model.CityModel;
import com.pragma.home360.domain.model.DepartmentModel;
import com.pragma.home360.domain.ports.in.CityServicePort;
import com.pragma.home360.domain.ports.out.CityPersistencePort;
import com.pragma.home360.domain.ports.out.DepartmentPersistencePort;


import java.util.List;

public class CityUseCase implements CityServicePort {
    private final CityPersistencePort cityPersistencePort;
    private final DepartmentPersistencePort departmentPersistencePort;



    public CityUseCase(CityPersistencePort cityPersistencePort,
                      DepartmentPersistencePort departmentPersistencePort)
    {
        this.departmentPersistencePort = departmentPersistencePort;
        this.cityPersistencePort = cityPersistencePort;
    }

    @Override
    public void save(CityModel cityModel, String departmentName) {
        if (cityModel.getName() == null || cityModel.getName().isBlank()) {
            throw new CityCannotBeNullException("El nombre de la ciudad no puede ser nulo");
        }
        if (cityModel.getDescription() == null || cityModel.getDescription().isBlank()) {
            throw new CityCannotBeNullException("La descripcion de la ciudad no puede ser nula");
        }

        DepartmentModel departmentModel = departmentPersistencePort.getByName(departmentName)        // Busca el departamento usando el puerto
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));


        cityModel.setDepartment(departmentModel);


        cityPersistencePort.save(cityModel);
    }

    @Override
    public List<CityModel> getCities(Integer page, Integer size, boolean orderAsc) {
        return cityPersistencePort.getCities(page, size, orderAsc);
    }

}
