package com.pragma.home360.infrastructure.endpoints.rest;

import com.pragma.home360.application.dto.request.SaveHouseRequest;
import com.pragma.home360.application.dto.response.HouseResponse;
import com.pragma.home360.application.dto.response.SaveHouseResponse;
import com.pragma.home360.application.services.HouseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/house")
@RequiredArgsConstructor
@Tag(name = "House", description = "House API")
public class HouseController {
    private final HouseService houseService;

    @PostMapping("/")
    public ResponseEntity<SaveHouseResponse> save(SaveHouseRequest houseRequest) {
        SaveHouseResponse response = houseService.save(houseRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/")
    public ResponseEntity<List<HouseResponse>> getHouses(Integer page, Integer size, boolean orderAsc) {
        List<HouseResponse> houses = houseService.getHouses(page, size, orderAsc);
        return ResponseEntity.ok(houses);
    }

}
