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
}