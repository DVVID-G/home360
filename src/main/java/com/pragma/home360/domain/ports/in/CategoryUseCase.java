package com.pragma.home360.domain.ports.in;

import com.pragma.home360.domain.model.Category;

public interface CategoryUseCase {
    Category createCategory(Category category);
}