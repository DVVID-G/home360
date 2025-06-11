package com.pragma.home360.infrastructure.endpoints.rest;

import com.pragma.home360.application.dto.request.HouseSearchRequest;
import com.pragma.home360.application.dto.request.SaveHouseRequest;
import com.pragma.home360.application.dto.response.HouseResponse;
import com.pragma.home360.application.dto.response.SaveHouseResponse;
import com.pragma.home360.application.services.HouseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
    @GetMapping("/search")
    public ResponseEntity<Page<HouseResponse>> searchHouses(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Integer minRooms,
            @RequestParam(required = false) Integer maxRooms,
            @RequestParam(required = false) Integer minBathrooms,
            @RequestParam(required = false) Integer maxBathrooms,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        HouseSearchRequest searchRequest = new HouseSearchRequest(
                location, category, minRooms, maxRooms,
                minBathrooms, maxBathrooms, minPrice, maxPrice,
                sortBy, sortDirection
        );

        Page<HouseResponse> result = houseService.searchHouses(page, size, searchRequest);
        return ResponseEntity.ok(result);
    }
}



