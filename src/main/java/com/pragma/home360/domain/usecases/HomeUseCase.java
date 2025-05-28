package com.pragma.home360.domain.usecases;

import com.pragma.home360.domain.model.HouseModel;
import com.pragma.home360.domain.ports.in.HouseServicePort;

import java.util.List;

public class HomeUseCase implements HouseServicePort {
    private final HouseServicePort houseServicePort;

    public HomeUseCase(HouseServicePort houseServicePort) {
        this.houseServicePort = houseServicePort;
    }

    @Override
    public void save(HouseModel houseModel){

        houseServicePort.save(houseModel);
    }

    @Override
    public List<HouseModel> getHouses(Integer page, Integer size, boolean orderAsc) {
        return houseServicePort.getHouses(page, size, orderAsc);
    }
}
