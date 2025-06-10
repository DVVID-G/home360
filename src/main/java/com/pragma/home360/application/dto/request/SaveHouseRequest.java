package com.pragma.home360.application.dto.request;

import java.time.LocalDateTime;

public record SaveHouseRequest (String name, String description, String categoryName, Integer numberOfRooms, Integer numberOfBathrooms, Double price, Long locationId,LocalDateTime publishDate){

}
