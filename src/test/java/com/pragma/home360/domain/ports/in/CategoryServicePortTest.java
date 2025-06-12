package com.pragma.home360.domain.ports.in;

import com.pragma.home360.application.services.implementation.CategoryServiceImpl;
import com.pragma.home360.domain.model.CategoryModel;
import com.pragma.home360.infrastructure.adapters.persistence.CategoryPersistenceAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CategoryServicePortTest {
    @Mock
    private CategoryPersistenceAdapter categoryPersistenceAdapter;
    @InjectMocks
    private CategoryServiceImpl categoryServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);}

    @Test
    void save() {
        CategoryModel categoryModel = new CategoryModel(null, "casa","Categoria de casas" );
        categoryPersistenceAdapter.save(categoryModel);
        assertEquals("casa", categoryModel.getName());

    }

    @Test
    void getCategories() {
        // Arrange: Preparamos una lista de categorías simulada
        CategoryModel categoryModel = new CategoryModel(1L, "casa", "Categoria de casas");
        List<CategoryModel> mockList = List.of(categoryModel);
        when(categoryPersistenceAdapter.getCategories(0, 1, true)).thenReturn(mockList);

        // Act: Llamamos al método y capturamos el resultado
        List<CategoryModel> result = categoryPersistenceAdapter.getCategories(0, 1, true);

        // Assert: Verificamos que la lista tenga el contenido esperado
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("casa", result.get(0).getName());

    }

}