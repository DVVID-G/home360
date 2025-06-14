package com.pragma.home360.domain.usecases;



import com.pragma.home360.domain.exceptions.CategoryAlreadyExistsException;
import com.pragma.home360.domain.model.CategoryModel;
import com.pragma.home360.domain.ports.in.CategoryServicePort;
import com.pragma.home360.domain.ports.out.CategoryPersistencePort;

import java.util.Collections;
import java.util.List;

public class CategoryUseCase implements CategoryServicePort {
    private final CategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(CategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public void save(CategoryModel categoryModel) {
        CategoryModel category = categoryPersistencePort.getCategoryByName(categoryModel.getName());
        if (categoryModel.getName() == null || categoryModel.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la categoría es obligatorio");
        }
        if (categoryModel.getDescription() == null || categoryModel.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción de la categoría es obligatoria");
        }
        if (category != null) {
            throw new CategoryAlreadyExistsException();
        }
        categoryPersistencePort.save(categoryModel);
    }

    @Override
    public List<CategoryModel> getCategories(Integer page, Integer size, boolean orderAsc) {
        return categoryPersistencePort.getCategories(page, size, orderAsc);
    }

    @Override
    public List<CategoryModel> getCategoryByName(String categoryName) {
        return Collections.singletonList(categoryPersistencePort.getCategoryByName(categoryName));
    }

    @Override
    public void deleteCategory(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID de la categoría es obligatorio y debe ser mayor que cero");
        }
        categoryPersistencePort.deleteCategory(id);
    }
}