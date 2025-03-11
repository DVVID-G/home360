package com.pragma.home360.application.services;

import com.pragma.home360.domain.model.Category;
import com.pragma.home360.domain.ports.out.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category saveCategory(Category category) {
        // Aquí puedes agregar lógica de negocio antes de guardar la categoría
        return categoryRepository.save(category);
    }
}