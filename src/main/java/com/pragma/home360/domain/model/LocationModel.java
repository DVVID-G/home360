package com.pragma.home360.domain.model;

public class LocationModel {
    private Long id;
    private CityModel city;
    private String barrio;
    private Long cityId;

    public LocationModel(Long id, CityModel city, String barrio, Long cityId) {
        this.id = id;
        this.city = city;
        this.barrio = barrio;
        this.cityId = cityId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CityModel getCity() {
        return city;
    }

    public void setCity(CityModel city) {
        this.city = city;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
}
