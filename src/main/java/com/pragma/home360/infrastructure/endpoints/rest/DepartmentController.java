package com.pragma.home360.infrastructure.endpoints.rest;

import com.pragma.home360.application.dto.request.SaveDepartmentRequest;
import com.pragma.home360.application.dto.response.DepartmentResponse;
import com.pragma.home360.application.dto.response.SaveDepartmentResponse;
import com.pragma.home360.application.services.DepartmentService;
import com.pragma.home360.infrastructure.constants.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
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
@RequestMapping(ApiConstants.DEPARTMENT_BASE)
@RequiredArgsConstructor
@Tag(name = "Department", description = "Department API")

public class DepartmentController {
    private final DepartmentService departmentService;
    @Operation(summary = "Crear un departamento"
            , description = "Este endpoint guarda un departamento en la BD")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Departamento creado exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SaveDepartmentResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })

    @PostMapping("/")
    public ResponseEntity<SaveDepartmentResponse> save(@RequestBody SaveDepartmentRequest saveDepartmentRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.save(saveDepartmentRequest));
    }
    @Operation(summary = "Obtener lista de departamentos",
            description = "Este endpoint obtiene una lista de departamentos paginada")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de departamentos obtenida exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DepartmentResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })

    @GetMapping("")
    public ResponseEntity<List<DepartmentResponse>> getDepartments(@RequestParam Integer page,
                                                                  @RequestParam Integer size,
                                                                  @RequestParam boolean orderAsc) {
        return ResponseEntity.ok(departmentService.getDepartments(page, size, orderAsc));
    }
}
