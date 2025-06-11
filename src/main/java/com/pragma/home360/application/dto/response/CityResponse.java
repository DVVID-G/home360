package com.pragma.home360.application.dto.response;

import com.pragma.home360.domain.model.DepartmentModel;

public record CityResponse (Long id, String cityName, String cityDescription, DepartmentModel departmentModel) {
}
