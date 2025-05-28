package com.pragma.home360.domain.model;

import java.time.LocalDateTime;

public class HouseModel {
    private Long id;
    private String name;
    private String description;
    private CategoryModel category;
    private Integer numberOfRooms;
    private Integer numberOfBathrooms;
    private Double price;
    private LocationModel location;
    private LocalDateTime publishDate;
    private PublicationStatusModel publicationStatus;
    private LocalDateTime createdAt;

    public HouseModel(Long id, String name, String description, CategoryModel category, Integer numberOfRooms, Integer numberOfBathrooms, Double price, LocationModel location, LocalDateTime publishDate, PublicationStatusModel publicationStatus, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.numberOfRooms = numberOfRooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.price = price;
        this.location = location;
        this.publishDate = publishDate;
        this.publicationStatus = publicationStatus;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public Integer getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public Double getPrice() {
        return price;
    }

    public LocationModel getLocation() {
        return location;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public PublicationStatusModel getPublicationStatus() {
        return publicationStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public void setNumberOfBathrooms(Integer numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public void setPublicationStatus(PublicationStatusModel publicationStatus) {
        this.publicationStatus = publicationStatus;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

