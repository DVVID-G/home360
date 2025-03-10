package com.pragma.home360.domain.ports.out;

import com.pragma.home360.domain.model.Category;

public interface CategoryRepository {
    Category save(Category category);
}