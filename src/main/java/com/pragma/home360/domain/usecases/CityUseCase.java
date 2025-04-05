package com.pragma.home360.domain.usecases;

import com.pragma.home360.domain.model.CityModel;
import com.pragma.home360.domain.model.DeparmentModel;
import com.pragma.home360.domain.ports.in.CityServicePort;
import com.pragma.home360.domain.ports.out.CityPersistencePort;
import com.pragma.home360.domain.ports.out.DeparmentPersistencePort;

import java.util.List;

public class CityUseCase implements CityServicePort {
    private final CityPersistencePort cityPersistencePort;
    private final DeparmentPersistencePort deparmentPersistencePort;



    public CityUseCase(CityPersistencePort cityPersistencePort,
                      DeparmentPersistencePort deparmentPersistencePort)
    {
        this.deparmentPersistencePort = deparmentPersistencePort;
        this.cityPersistencePort = cityPersistencePort;
    }

    @Override
    public void save(CityModel cityModel, String deparmentName) {
        DeparmentModel deparmentModel= deparmentPersistencePort.getByName(deparmentName);
        cityModel.setDeparmentId(deparmentModel.getId());
        cityPersistencePort.save(cityModel);
    }

    @Override
    public List<CityModel> getCities(Integer page, Integer size, boolean orderAsc) {
        return cityPersistencePort.getCities(page, size, orderAsc);
    }

}
