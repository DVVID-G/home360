package com.pragma.home360.domain.exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String categoryName
    ) {
        super("Categoría no encontrada: " + categoryName
        );
    }
}
