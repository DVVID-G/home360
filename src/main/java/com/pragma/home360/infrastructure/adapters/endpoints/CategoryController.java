package com.pragma.home360.infrastructure.adapters.endpoints;

import com.pragma.home360.application.dto.request.SaveCategoryRequest;
import com.pragma.home360.application.dto.response.SaveCategoryResponse;
import com.pragma.home360.domain.ports.in.CategoryUseCase;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryUseCase categoryUseCase;

    public CategoryController(CategoryUseCase categoryUseCase) {
        this.categoryUseCase = categoryUseCase;
    }

    @PostMapping
    public SaveCategoryResponse createCategory(@RequestBody SaveCategoryRequest request) {
        // Mapear request a modelo de dominio y llamar al caso de uso
        return new SaveCategoryResponse();
    }
}