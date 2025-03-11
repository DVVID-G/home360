package com.pragma.home360.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    @NotBlank(message = "El nombre de la categoría no puede estar vacío")
    @Size(max = 50, message = "El nombre de la categoría no puede tener más de 50 caracteres")
    private String name;
    @NotBlank(message = "La descripción de la categoría no puede estar vacía")
    @Size(max = 90, message = "La descripción de la categoría no puede tener más de 90 caracteres")
    private String description;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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
}


