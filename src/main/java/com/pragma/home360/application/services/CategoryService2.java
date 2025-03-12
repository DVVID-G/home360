package com.pragma.home360.application.services;

import com.pragma.home360.domain.model.Category;
import com.pragma.home360.domain.ports.out.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService2 {

    private final CategoryRepository categoryRepository;

    public CategoryService2(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category saveCategory(Category category) {
        // Validar que el nombre no esté duplicado
        if (categoryRepository.existsByName(category.getName())) {
            throw new IllegalArgumentException("El nombre de la categoría ya existe");
        }

        // Validar que la descripción no esté vacía
        if (category.getDescription() == null || category.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción de la categoría no puede estar vacía");
        }

        // Validar que el nombre no supere los 50 caracteres
        if (category.getName().length() > 50) {
            throw new IllegalArgumentException("El nombre de la categoría no puede tener más de 50 caracteres");
        }

        // Validar que la descripción no supere los 90 caracteres
        if (category.getDescription().length() > 90) {
            throw new IllegalArgumentException("La descripción de la categoría no puede tener más de 90 caracteres");
        }

        // Guardar la categoría
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }


}