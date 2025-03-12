package com.pragma.home360.infrastructure.adapters.endpoints;

import com.pragma.home360.application.dto.request.SaveCategoryRequest;
import com.pragma.home360.application.dto.response.SaveCategoryResponse;
import com.pragma.home360.application.services.CategoryService;
import com.pragma.home360.domain.model.Category;
import com.pragma.home360.domain.ports.in.CategoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor // Inyección de dependencias por constructor
public class CategoryController {
    private final CategoryUseCase categoryUseCase;
    private final CategoryService categoryService;


    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody SaveCategoryRequest request) {
        categoryService.save(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}