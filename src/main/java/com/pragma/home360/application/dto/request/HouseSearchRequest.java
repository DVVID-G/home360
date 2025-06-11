package com.pragma.home360.application.dto.request;

import java.math.BigDecimal;

public record HouseSearchRequest(
        String location,
        String category,
        Integer minRooms,
        Integer maxRooms,
        Integer minBathrooms,
        Integer maxBathrooms,
        BigDecimal minPrice,
        BigDecimal maxPrice,
        String sortBy,      // "location", "category", "rooms", "bathrooms", "price"
        String sortDirection // "asc", "desc"
) {
}
