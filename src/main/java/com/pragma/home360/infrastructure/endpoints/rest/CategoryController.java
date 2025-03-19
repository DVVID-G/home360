package com.pragma.home360.infrastructure.endpoints.rest;

import com.pragma.home360.application.dto.request.SaveCategoryRequest;
import com.pragma.home360.application.dto.response.CategoryResponse;
import com.pragma.home360.application.dto.response.SaveCategoryResponse;
import com.pragma.home360.application.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor // Inyección de dependencias por constructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<SaveCategoryResponse> save(@RequestBody SaveCategoryRequest SavecategoryRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(SavecategoryRequest));
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryResponse>> getAllCategories(@RequestParam Integer page, @RequestParam Integer size,
                                                                   @RequestParam boolean orderAsc) {
        return ResponseEntity.ok(categoryService.getCategories(page, size, orderAsc));
    }

}