package com.pragma.home360.domain.model;

import java.math.BigDecimal;

public class HouseSearchFilters {
    private final String location;
    private final String category;
    private final Integer minRooms;
    private final Integer maxRooms;
    private final Integer minBathrooms;
    private final Integer maxBathrooms;
    private final BigDecimal minPrice;
    private final BigDecimal maxPrice;
    private final String sortBy;
    private final String sortDirection;

    public HouseSearchFilters(String location, String category, Integer minRooms, Integer maxRooms,
                              Integer minBathrooms, Integer maxBathrooms, BigDecimal minPrice, BigDecimal maxPrice,
                              String sortBy, String sortDirection) {
        this.location = location;
        this.category = category;
        this.minRooms = minRooms;
        this.maxRooms = maxRooms;
        this.minBathrooms = minBathrooms;
        this.maxBathrooms = maxBathrooms;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.sortBy = sortBy;
        this.sortDirection = sortDirection;
    }

    // Getters
    public String getLocation() { return location; }
    public String getCategory() { return category; }
    public Integer getMinRooms() { return minRooms; }
    public Integer getMaxRooms() { return maxRooms; }
    public Integer getMinBathrooms() { return minBathrooms; }
    public Integer getMaxBathrooms() { return maxBathrooms; }
    public BigDecimal getMinPrice() { return minPrice; }
    public BigDecimal getMaxPrice() { return maxPrice; }
    public String getSortBy() { return sortBy; }
    public String getSortDirection() { return sortDirection; }
}
