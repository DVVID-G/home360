package com.pragma.home360.domain.model;


import com.pragma.home360.domain.exceptions.DescriptionMaxSizeExceededException;
import com.pragma.home360.domain.exceptions.NameMaxSizeExceededException;

public class CityModel {
    private Long id;
    private String name;
    private String description;
    private DepartmentModel department;

    public CityModel() { }
    public CityModel(Long id, String name, String description, DepartmentModel department) {
        if (name.length() > 50) throw new NameMaxSizeExceededException();
        if (description.length() > 120) throw new DescriptionMaxSizeExceededException();
        this.id = id;
        this.name = name;
        this.description = description;
        this.department = department;
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

    public DepartmentModel getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentModel department) {
        this.department = department;
    }
}
