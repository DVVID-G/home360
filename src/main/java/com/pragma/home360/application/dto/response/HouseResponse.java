package com.pragma.home360.application.dto.response;

import java.time.LocalDateTime;

public class HouseResponse {
    private Long id;
    private String name;
    private String description;
    private CategoryResponse category;
    private Integer numberOfRooms;
    private Integer numberOfBathrooms;
    private Double price;
    private LocationResponse location;
    private LocalDateTime publishDate;
    private String status;
    private LocalDateTime createdAt;

}
