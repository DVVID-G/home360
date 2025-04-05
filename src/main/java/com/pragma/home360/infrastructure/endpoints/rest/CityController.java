package com.pragma.home360.infrastructure.endpoints.rest;

import com.pragma.home360.application.dto.request.SaveCityRequest;
import com.pragma.home360.application.dto.response.CityResponse;
import com.pragma.home360.application.dto.response.SaveCityResponse;
import com.pragma.home360.application.services.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/City")
@RequiredArgsConstructor

public class CityController {
    private final CityService cityService;

    @PostMapping("/")
    public ResponseEntity<SaveCityResponse> save(@RequestBody SaveCityRequest saveCityRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cityService.save(saveCityRequest));
    }

    @GetMapping("/")
    public ResponseEntity<List<CityResponse>> getCities(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "15") Integer size,
            @RequestParam(value = "orderAsc", defaultValue = "false") boolean orderAsc) {
        return ResponseEntity.ok(cityService.getCities(page, size, orderAsc));
    }
}
