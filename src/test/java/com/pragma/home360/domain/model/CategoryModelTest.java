package com.pragma.home360.domain.model;

import com.pragma.home360.domain.exceptions.DescriptionMaxSizeExceededException;
import com.pragma.home360.domain.exceptions.NameMaxSizeExceededException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    @Test
    void constructorNameIsTooLong() {
        String longName = "a".repeat(51); // 51 caracteres
        // Se espera que se lance NameMaxSizeExceededException
        assertThrows(NameMaxSizeExceededException.class, () ->
                new CategoryModel(1L, longName, "description"));
    }

    @Test
    void constructorDescriptionIsTooLong() {
        String longDescription = "a".repeat(121);
        assertThrows(DescriptionMaxSizeExceededException.class, () ->
                new CategoryModel(1L, "name", longDescription));
    }



}