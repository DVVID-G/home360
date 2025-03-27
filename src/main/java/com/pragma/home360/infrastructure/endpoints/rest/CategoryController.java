package com.pragma.home360.infrastructure.endpoints.rest;

import com.pragma.home360.application.dto.request.SaveCategoryRequest;
import com.pragma.home360.application.dto.response.CategoryResponse;
import com.pragma.home360.application.dto.response.SaveCategoryResponse;
import com.pragma.home360.application.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor // Inyección de dependencias por constructor
@Tag(name = "Category", description = "Category API")
public class CategoryController {
    private final CategoryService categoryService;

    @Operation (summary = "Crear una nueva categoria"
    , description = "Este endpoint guarda una nueva categoria en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Categoría creada exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            // Ajusta la clase que corresponde a la respuesta
                            schema = @Schema(implementation = SaveCategoryResponse.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de respuesta exitosa",
                                    value = """
                {
                  "id": 1,
                  "name": "Casa",
                  "description": "Categoría de casas"
                }
                """
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/")
    public ResponseEntity<SaveCategoryResponse> save(@RequestBody SaveCategoryRequest savecategoryRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(savecategoryRequest));
    }

    @Operation (summary = "Obtener todas las categorias", description = "Retorna la lista de categorías en una paginación configurable (page, size).")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Listado de categorías obtenido exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = CategoryResponse.class)
                            ),
                            examples = @ExampleObject(
                                    name = "Ejemplo de respuesta exitosa",
                                    value = """
                    [
                      {
                        "id": 1,
                        "name": "Casa",
                        "description": "Categoría de casas"
                      },
                      {
                        "id": 2,
                        "name": "Apartamento",
                        "description": "Categoría de apartamentos"
                      }
                    ]
                    """
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Parámetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/")
    public ResponseEntity<List<CategoryResponse>> getAllCategories(@RequestParam Integer page, @RequestParam Integer size,
                                                                   @RequestParam boolean orderAsc) {
        return ResponseEntity.ok(categoryService.getCategories(page, size, orderAsc));
    }
    @GetMapping("/byname")
    public ResponseEntity<List<CategoryResponse>> getCategoryByName(@RequestParam String categoryName) {
        return ResponseEntity.ok(categoryService.getCategoryByName(categoryName));
    }
}