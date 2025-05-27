package com.pragma.home360.infrastructure.endpoints.rest;

import com.pragma.home360.application.dto.request.SaveLocationRequest;
import com.pragma.home360.application.dto.response.LocationResponse;
import com.pragma.home360.application.dto.response.SaveLocationResponse;
import com.pragma.home360.application.services.LocationService;
import com.pragma.home360.domain.model.LocationModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Location")
@RequiredArgsConstructor
@Tag(name = "Location", description = "Location API")
public class LocationController {
    private final LocationService locationService;
    @Operation(summary = "Crear una nueva ubicacion"
            , description = "Este endpoint guarda una nueva ubicacion en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Ubicación creada exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SaveLocationResponse.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de respuesta exitosa",
                                    value = """
                                            {
                                              "id": 1,
                                              "name": "Ubicacion",
                                              "description": "Ubicacion de prueba"
                                            }
                                            """
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })

    @PostMapping("/")
    public ResponseEntity<SaveLocationResponse> save(@RequestBody SaveLocationRequest saveLocationRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(locationService.save(saveLocationRequest));
    }
    @Operation(summary = "Obtener lista de ubicaciones",
            description = "Este endpoint obtiene una lista de ubicaciones paginada")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de ubicaciones obtenida exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LocationModel.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })

    @GetMapping("/")
    public ResponseEntity<Page<LocationResponse>> getLocations(Pageable pageable) {


        return ResponseEntity.ok(locationService.getLocations(pageable.getPageNumber(), pageable.getPageSize(), pageable.isPaged()));
    }

    @GetMapping("/search")
    public ResponseEntity<List<LocationResponse>> searchLocations(@RequestParam String searchText) {
        return ResponseEntity.ok(locationService.searchLocations(searchText));
    }

}
