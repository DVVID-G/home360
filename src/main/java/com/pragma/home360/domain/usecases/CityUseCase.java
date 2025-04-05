package com.pragma.home360.domain.usecases;

import com.pragma.home360.domain.model.CityModel;
import com.pragma.home360.domain.ports.in.CityServicePort;
import com.pragma.home360.domain.ports.out.CityPersistencePort;

import java.util.List;

public class CityUseCase implements CityServicePort {
    private final CityPersistencePort cityPersistencePort;



    public CityUseCase(CityPersistencePort cityPersistencePort) {
        this.cityPersistencePort = cityPersistencePort;
    }

    @Override
    public void save(CityModel cityModel) {
        cityPersistencePort.save(cityModel);
    }

    @Override
    public List<CityModel> getCities(Integer page, Integer size, boolean orderAsc) {
        return cityPersistencePort.getCities(page, size, orderAsc);
    }

}
