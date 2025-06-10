package com.pragma.home360.domain.ports.out;

import com.pragma.home360.domain.model.HouseModel;

import java.util.List;

public interface HousePersistencePort {
    void save(HouseModel houseModel);
    List<HouseModel> getHouses(Integer page, Integer size, boolean orderAsc);
}
