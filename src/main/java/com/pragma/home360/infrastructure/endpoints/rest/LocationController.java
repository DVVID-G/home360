package com.pragma.home360.infrastructure.endpoints.rest;

import com.pragma.home360.application.dto.request.SaveLocationRequest;
import com.pragma.home360.application.dto.response.SaveLocationResponse;
import com.pragma.home360.application.services.LocationService;
import com.pragma.home360.domain.model.LocationModel;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Location")
@RequiredArgsConstructor
@Tag(name = "Location", description = "Location API")
public class LocationController {
    private final LocationService locationService;

    @PostMapping("/")
    public ResponseEntity<SaveLocationResponse> save(@RequestBody SaveLocationRequest saveLocationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(locationService.save(saveLocationRequest));
    }

    @GetMapping("/")
    public ResponseEntity<List<LocationModel>> getLocations(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "orderAsc", defaultValue = "true") boolean orderAsc) {
        return ResponseEntity.ok(locationService.getLocations(page, size, orderAsc));
    }
}
