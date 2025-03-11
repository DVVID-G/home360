/*
package com.pragma.home360.domain.infrastructure.adapters.repositories.mysql;


import com.pragma.home360.domain.model.Category;
import com.pragma.home360.infrastructure.repositories.mysql.CategoryRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Usa la base de datos configurada
class CategoryRepositoryImplTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CategoryRepositoryImpl categoryRepositoryImpl;

    @Test
    void save() {
        // Arrange
        Category category = new Category();
        category.setName("Casa");
        category.setDescription("Casa de lujo");

        // Act
        Category result = categoryRepositoryImpl.save(category);

        // Assert
        assertNotNull(result); // Verifica que el resultado no sea nulo
        assertNotNull(result.getId()); // Verifica que se generó un ID
        assertEquals("Casa", result.getName());
        assertEquals("Casa de lujo", result.getDescription());

        // Verifica que la categoría se guardó en la base de datos
        Category savedCategory = entityManager.find(Category.class, result.getId());
        assertNotNull(savedCategory);
        assertEquals("Casa", savedCategory.getName());
        assertEquals("Casa de lujo", savedCategory.getDescription());
    }
}

 */
