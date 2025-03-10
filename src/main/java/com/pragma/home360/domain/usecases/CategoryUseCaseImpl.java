package com.pragma.home360.domain.usecases;

import com.pragma.home360.domain.model.Category;
import com.pragma.home360.domain.ports.in.CategoryUseCase;
import com.pragma.home360.domain.ports.out.CategoryRepository;

public class CategoryUseCaseImpl implements CategoryUseCase {
    private final CategoryRepository categoryRepository;

    public CategoryUseCaseImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }
}