package com.pragma.home360.application.mappers;

import com.pragma.home360.application.dto.request.SaveCategoryRequest;
import com.pragma.home360.application.dto.response.CategoryResponse;
import com.pragma.home360.domain.model.CategoryModel;
import com.pragma.home360.infrastructure.entities.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryDtoMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    CategoryModel requestToModel(SaveCategoryRequest saveCategoryRequest);
    CategoryResponse modelToResponse(CategoryModel categoryModel);
    List<CategoryResponse> modelListToResponseList(List<CategoryModel> categories);
    CategoryResponse entityToResponse(CategoryEntity categoryEntity);
}
