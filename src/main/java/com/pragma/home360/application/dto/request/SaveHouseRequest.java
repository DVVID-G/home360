package com.pragma.home360.application.dto.request;

import java.time.LocalDateTime;

public class SaveHouseRequest {
    private String name;
    private String description;
    private Long categoryId;
    private Integer numberOfRooms;
    private Integer numberOfBathrooms;
    private Double price;
    private Long locationId;
    private LocalDateTime publishDate;

}
