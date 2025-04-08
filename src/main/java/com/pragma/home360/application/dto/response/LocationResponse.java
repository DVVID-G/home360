package com.pragma.home360.application.dto.response;


import com.pragma.home360.domain.model.CityModel;
import com.pragma.home360.domain.model.DepartmentModel;

public record LocationResponse(Long id, String barrio, CityModel cityModel, DepartmentModel departmentModel) {

}
