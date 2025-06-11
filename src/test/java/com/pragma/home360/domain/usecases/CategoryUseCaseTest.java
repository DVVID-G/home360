package com.pragma.home360.domain.usecases;

import com.pragma.home360.domain.model.CategoryModel;
import com.pragma.home360.domain.ports.out.CategoryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryUseCaseTest {
    @Mock
    CategoryPersistencePort categoryPersistencePort;
    @InjectMocks
    CategoryUseCase categoryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        CategoryModel categoryModel = new CategoryModel(1L, "name", "description");
        categoryUseCase.save(categoryModel);
        assertNotNull(categoryModel.getId());
    }

    @Test
    void saveCategoryAlreadyExists() {
        CategoryModel categoryModel = new CategoryModel(1L, "name", "description");
        when(categoryPersistencePort.getCategoryByName("name")).thenReturn(categoryModel);
        assertThrows(Exception.class, () -> categoryUseCase.save(categoryModel));
    }

    @Test
    void getCategories() {
        // Arrange
        List<CategoryModel> mockCategories = Arrays.asList(
                new CategoryModel(1L, "Casa", "Categoría de casas"),
                new CategoryModel(2L, "Apartamento", "Categoría de apartamentos")
        );
        when(categoryPersistencePort.getCategories(0, 10, true)).thenReturn(mockCategories);

        // Act
        List<CategoryModel> categories = categoryUseCase.getCategories(0, 10, true);

        // Assert
        assertNotNull(categories);
        assertEquals(2, categories.size());
        assertEquals("Casa", categories.get(0).getName());
        assertEquals("Apartamento", categories.get(1).getName());
    }


    @Test
    void getCategoryByName() {
        // Arrange
        CategoryModel mockCategory = new CategoryModel(1L, "Casa", "Categoría de casas");
        when(categoryPersistencePort.getCategoryByName("Casa")).thenReturn(mockCategory);

        // Act
        List<CategoryModel> categories = categoryUseCase.getCategoryByName("Casa");

        // Assert
        assertNotNull(categories);
        assertEquals(1, categories.size());
        assertEquals("Casa", categories.get(0).getName());
    }
}