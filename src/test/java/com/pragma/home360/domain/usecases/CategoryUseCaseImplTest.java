package com.pragma.home360.domain.usecases;

import com.pragma.home360.domain.model.Category;
import com.pragma.home360.domain.ports.out.CategoryRepository;
import com.pragma.home360.domain.usecases.CategoryUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryUseCaseImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryUseCaseImpl categoryUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCategory() {
        // Arrange
        Category category = new Category();
        category.setName("Casa");
        category.setDescription("Casa de lujo");

        when(categoryRepository.save(category)).thenReturn(category);

        // Act
        Category result = categoryUseCase.createCategory(category);

        // Assert
        assertNotNull(result);
        assertEquals("Casa", result.getName());
        assertEquals("Casa de lujo", result.getDescription());
        verify(categoryRepository, times(1)).save(category);
    }
}