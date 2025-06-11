package com.pragma.home360.domain.ports.in;

import com.pragma.home360.domain.model.CityModel;

import java.util.List;

public interface CityServicePort {
    void save(CityModel cityModel, String departmentName);
    List<CityModel> getCities(Integer page, Integer size, boolean orderAsc);
}
