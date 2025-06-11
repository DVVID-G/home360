package com.pragma.home360.application.services;

import com.pragma.home360.application.dto.request.HouseSearchRequest;
import com.pragma.home360.application.dto.request.SaveHouseRequest;
import com.pragma.home360.application.dto.response.HouseResponse;
import com.pragma.home360.application.dto.response.SaveHouseResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HouseService {
    SaveHouseResponse save(SaveHouseRequest houseRequest);
    List<HouseResponse> getHouses(Integer page, Integer size, boolean orderAsc);
    Page<HouseResponse> searchHouses(Integer page, Integer size, HouseSearchRequest searchRequest);
}
