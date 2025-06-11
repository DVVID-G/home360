package com.pragma.home360.domain.ports.out;

import com.pragma.home360.domain.model.HouseModel;
import com.pragma.home360.domain.model.HouseSearchFilters;
import com.pragma.home360.domain.model.PaginatedResult;

import java.time.LocalDateTime;
import java.util.List;

public interface HousePersistencePort {
    void save(HouseModel houseModel);
    List<HouseModel> getHouses(Integer page, Integer size, boolean orderAsc);
    PaginatedResult<HouseModel> getHousesWithFilters(
            Integer page,
            Integer size,
            HouseSearchFilters filters,
            LocalDateTime currentDate // ← Domain proporciona la fecha
    );

}
