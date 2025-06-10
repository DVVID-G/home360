package com.pragma.home360.domain.usecases;

import com.pragma.home360.domain.model.HouseModel;
import com.pragma.home360.domain.ports.in.HouseServicePort;
import com.pragma.home360.domain.ports.out.HousePersistencePort;

import java.util.List;

public class HouseUseCase implements HouseServicePort {
    private final HousePersistencePort housePersistencePort;

    public HouseUseCase(HousePersistencePort housePersistencePort) {
        this.housePersistencePort = housePersistencePort;
    }

    @Override
    public void save(HouseModel houseModel){
        housePersistencePort.save(houseModel);
    }

    @Override
    public List<HouseModel> getHouses(Integer page, Integer size, boolean orderAsc) {
        return housePersistencePort.getHouses(page, size, orderAsc);
    }
}
