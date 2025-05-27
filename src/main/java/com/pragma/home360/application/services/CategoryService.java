package com.pragma.home360.application.services;

import com.pragma.home360.application.dto.request.SaveCategoryRequest;
import com.pragma.home360.application.dto.response.CategoryResponse;
import com.pragma.home360.application.dto.response.SaveCategoryResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    SaveCategoryResponse save(SaveCategoryRequest request);
    Page<CategoryResponse> getCategories(Integer page, Integer size, boolean orderAsc);
    List<CategoryResponse> getCategoryByName(String categoryName);
    void deleteCategory(Long id);
}
