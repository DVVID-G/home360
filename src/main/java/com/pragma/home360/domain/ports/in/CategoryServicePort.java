package com.pragma.home360.domain.ports.in;


import com.pragma.home360.domain.model.CategoryModel;
import java.util.List;

public interface CategoryServicePort {
    void save(CategoryModel categoryModel);
    List<CategoryModel> getCategories(Integer page, Integer size, boolean orderAsc);

    List<CategoryModel> getCategoryByName(String categoryName);
}