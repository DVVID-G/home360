package com.pragma.home360.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryModelTest {

    private CategoryModel category;

    @BeforeEach
    void setUp() {
        category = new CategoryModel(1L, "name", "description");
    }

    @Test
    void getId() {
        assertEquals(1L, category.getId());
    }

    @Test
    void setId() {
        category.setId(2L);
        assertEquals(2L, category.getId());
    }

    @Test
    void getName() {
        assertEquals("name", category.getName());
    }

    @Test
    void setName() {
        category.setName("newName");
        assertEquals("newName", category.getName());
    }

    @Test
    void getDescription() {
        assertEquals("description", category.getDescription());
    }

    @Test
    void setDescription() {
        category.setDescription("Un apartamento moderno");
        assertEquals("Un apartamento moderno", category.getDescription());

    }
}