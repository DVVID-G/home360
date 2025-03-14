package com.pragma.home360.application.services;

import com.pragma.home360.application.dto.request.SaveCategoryRequest;
import com.pragma.home360.application.dto.response.CategoryResponse;
import com.pragma.home360.application.dto.response.SaveCategoryResponse;

import java.util.List;

public interface CategoryService {//Interfaz que define los métodos que se deben implementar para el servicio de categorías.
    SaveCategoryResponse save(SaveCategoryRequest request);//Guarda una categoría en la base de datos y devuelve un objeto SaveCategoryResponse.
    List<CategoryResponse> getCategories(Integer page, Integer size, boolean orderAsc);//Devuelve una lista de categorías paginada y ordenada.
}
