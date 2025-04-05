package com.pragma.home360.domain.model;


public class CityModel {
    private Long id;
    private String name;
    private String description;
    private Long deparmentId;

    public CityModel() { }
    public CityModel(Long id, String name, String description, Long deparmentId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deparmentId = deparmentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDeparmentId() {
        return deparmentId;
    }

    public void setDeparmentId(Long deparmentId) {
        this.deparmentId = deparmentId;
    }
}
