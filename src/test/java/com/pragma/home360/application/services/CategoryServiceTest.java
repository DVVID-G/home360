package com.pragma.home360.application.services;

import com.pragma.home360.domain.model.Category;
import com.pragma.home360.domain.ports.out.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService2 categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Criterio 1: Cada categoría tiene 3 campos: id, nombre y descripción
    @Test
    void createCategory_ShouldReturnCategoryWithIdNameAndDescription() {
        // Arrange
        Category category = new Category();
        category.setName("Casa");
        category.setDescription("Descripción válida");

        when(categoryRepository.existsByName("Casa")).thenReturn(false);
        when(categoryRepository.save(category)).thenReturn(category);

        // Act
        Category savedCategory = categoryService.saveCategory(category);

        // Assert
        assertNotNull(savedCategory.getId()); // Verifica que se generó un ID
        assertEquals("Casa", savedCategory.getName());
        assertEquals("Descripción válida", savedCategory.getDescription());
    }

    // Criterio 2: El nombre de la categoría no se puede repetir
    @Test
    void createCategory_ShouldThrowException_WhenNameAlreadyExists() {
        // Arrange
        Category category = new Category();
        category.setName("Casa");
        category.setDescription("Descripción válida");

        when(categoryRepository.existsByName("Casa")).thenReturn(true);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryService.saveCategory(category);
        });

        assertEquals("El nombre de la categoría ya existe", exception.getMessage());
    }

    // Criterio 3: Todas las categorías deben tener una descripción
    @Test
    void createCategory_ShouldThrowException_WhenDescriptionIsEmpty() {
        // Arrange
        Category category = new Category();
        category.setName("Casa");
        category.setDescription(""); // Descripción vacía

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryService.saveCategory(category);
        });

        assertEquals("La descripción de la categoría no puede estar vacía", exception.getMessage());
    }

    // Criterio 4: El tamaño máximo del nombre debe ser de 50 caracteres
    @Test
    void createCategory_ShouldThrowException_WhenNameIsTooLong() {
        // Arrange
        Category category = new Category();
        category.setName("Este nombre es demasiado largo y supera los 50 caracteres permitidos");
        category.setDescription("Descripción válida");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryService.saveCategory(category);
        });

        assertEquals("El nombre de la categoría no puede tener más de 50 caracteres", exception.getMessage());
    }

    // Criterio 5: El tamaño máximo de la descripción debe ser de 90 caracteres
    @Test
    void createCategory_ShouldThrowException_WhenDescriptionIsTooLong() {
        // Arrange
        Category category = new Category();
        category.setName("Casa");
        category.setDescription("Esta descripción es demasiado larga y supera los 90 caracteres permitidos. Esto no debería ser válido.");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryService.saveCategory(category);
        });

        assertEquals("La descripción de la categoría no puede tener más de 90 caracteres", exception.getMessage());
    }
}