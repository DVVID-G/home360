package com.pragma.home360.domain.model;



public class LocationModel {
    private Long id;
    private CityModel city;
    private String barrio;
    private CityModel cityModel;

    public LocationModel(Long id, CityModel city, String barrio, CityModel cityModel) {
        this.id = id;
        this.city = city;
        this.barrio = barrio;
        this.cityModel = cityModel;
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

    public CityModel getCityModel() {
        return cityModel;
    }

    public void setCityModel(CityModel cityModel) {
        this.cityModel = cityModel;
    }
}
