package com.pragma.home360.domain.ports.in;

import com.pragma.home360.domain.model.HouseModel;

import java.util.List;

public interface HouseServicePort {
    void save(HouseModel houseModel);
    List<HouseModel> getHouses(Integer page, Integer size, boolean orderAsc);
}
