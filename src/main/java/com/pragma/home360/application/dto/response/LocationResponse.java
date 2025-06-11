package com.pragma.home360.application.dto.response;


import com.pragma.home360.domain.model.CityModel;


public record LocationResponse(Long id, String barrio, CityModel city) {

}
