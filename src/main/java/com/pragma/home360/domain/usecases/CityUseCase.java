package com.pragma.home360.domain.usecases;

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
        // Busca el departamento usando el puerto, no el repositorio
        DepartmentModel departmentModel = departmentPersistencePort.getByName(departmentName)
                .orElseThrow(() -> new RuntimeException("Departamento no encontrado"));

        // Asigna el departamento al modelo
        cityModel.setDepartment(departmentModel);

        // Guarda usando el puerto
        cityPersistencePort.save(cityModel);
    }

    @Override
    public List<CityModel> getCities(Integer page, Integer size, boolean orderAsc) {
        return cityPersistencePort.getCities(page, size, orderAsc);
    }

}
