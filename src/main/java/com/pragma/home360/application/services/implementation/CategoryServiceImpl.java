package com.pragma.home360.application.services.implementation;

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

    @Override
    public SaveCategoryResponse save(SaveCategoryRequest request) {
        categoryServicePort.save(categoryDtoMapper.requestToModel(request));
        return new SaveCategoryResponse(Constants.SAVE_CATEGORY_RESPONSE_MESSAGE, LocalDateTime.now());
    }

    @Override
    public List<CategoryResponse> getCategories(Integer page, Integer size, boolean orderAsc) {
        return categoryDtoMapper.modelListToResponseList(categoryServicePort.getCategories(page, size, orderAsc));
    }

}
