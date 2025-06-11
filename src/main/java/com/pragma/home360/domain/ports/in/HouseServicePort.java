package com.pragma.home360.domain.ports.in;

import com.pragma.home360.domain.model.HouseModel;
import com.pragma.home360.domain.model.HouseSearchFilters;
import com.pragma.home360.domain.model.PaginatedResult;

import java.util.List;

public interface HouseServicePort {
    void save(HouseModel houseModel, String categoryName, String LocationName, String status);
    List<HouseModel> getHouses(Integer page, Integer size, boolean orderAsc);
    PaginatedResult<HouseModel> getHousesWithFilters(Integer page, Integer size, HouseSearchFilters filters);

}
