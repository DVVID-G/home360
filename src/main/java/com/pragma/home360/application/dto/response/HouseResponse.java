package com.pragma.home360.application.dto.response;

import java.time.LocalDateTime;

public record HouseResponse (Long id,
String name,
String description,
CategoryResponse category,
Integer numberOfRooms,
Integer numberOfBathrooms,
Double price,
LocationResponse location,
LocalDateTime publishDate,
String status,
LocalDateTime createdAt){

}
