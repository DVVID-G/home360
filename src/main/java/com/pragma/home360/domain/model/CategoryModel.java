package com.pragma.home360.domain.model;


import com.pragma.home360.domain.exceptions.DescriptionMaxSizeExceededException;
import com.pragma.home360.domain.exceptions.NameMaxSizeExceededException;
import com.pragma.home360.domain.utils.constants.DomainConstants;

import java.util.Objects;

public class CategoryModel {

    private Long id;
    private String name;
    private String description;

    public CategoryModel(Long id, String name, String description) {
        this.name = Objects.requireNonNull(name, DomainConstants.FIELD_NAME_NULL_MESSAGE);
        this.description = Objects.requireNonNull(description, DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
        if (this.name.length() > 50) throw new NameMaxSizeExceededException();
        if (this.description.length() > 90) throw new DescriptionMaxSizeExceededException();
        this.id = id;
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
        if (name.length() > 50) throw new NameMaxSizeExceededException();
        this.name = Objects.requireNonNull(name, DomainConstants.FIELD_NAME_NULL_MESSAGE);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.length() > 90) throw new DescriptionMaxSizeExceededException();
        this.description = Objects.requireNonNull(description,  DomainConstants.FIELD_DESCRIPTION_NULL_MESSAGE);
    }
}




