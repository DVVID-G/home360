package com.pragma.home360.domain.ports.out;

import com.pragma.home360.domain.model.CityModel;

import java.util.List;

public interface CityPersistencePort {
    void save(CityModel cityModel);
    List<CityModel> getCities(Integer page, Integer size, boolean orderAsc);
    CityModel getByName(String cityName);
}
