package com.pragma.home360.application.services.implementation;

import com.pragma.home360.infrastructure.entities.CategoryEntity;
import com.pragma.home360.infrastructure.repositories.mysql.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.pragma.home360.application.dto.response.CategoryResponse;
import com.pragma.home360.application.dto.response.SaveCategoryResponse;
import com.pragma.home360.application.services.CategoryService;
import com.pragma.home360.infrastructure.configurations.utils.Constants;
import com.pragma.home360.domain.ports.in.CategoryServicePort;
import com.pragma.home360.application.dto.request.SaveCategoryRequest;
import com.pragma.home360.application.mappers.CategoryDtoMapper;


import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryServicePort categoryServicePort;
    private final CategoryDtoMapper categoryDtoMapper;
    private final CategoryRepository categoryRepository;

    @Override
    public SaveCategoryResponse save(SaveCategoryRequest request) {
        categoryServicePort.save(categoryDtoMapper.requestToModel(request));
        return new SaveCategoryResponse(Constants.SAVE_CATEGORY_RESPONSE_MESSAGE, LocalDateTime.now());
    }

    @Override
    public Page<CategoryResponse> getCategories(Integer page, Integer size, boolean orderAsc) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CategoryEntity> pageEntities = categoryRepository.findAll(pageable);
        return pageEntities.map(categoryDtoMapper::entityToResponse);
    }

    @Override
    public List<CategoryResponse> getCategoryByName(String categoryName) {
        return categoryDtoMapper.modelListToResponseList(categoryServicePort.getCategoryByName(categoryName));
    }

    @Override
    public void deleteCategory(Long id) {
        categoryServicePort.deleteCategory(id);
    }

}
