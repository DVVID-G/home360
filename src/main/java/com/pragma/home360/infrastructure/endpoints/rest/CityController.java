package com.pragma.home360.infrastructure.endpoints.rest;

import com.pragma.home360.application.dto.request.SaveCityRequest;
import com.pragma.home360.application.dto.response.CityResponse;
import com.pragma.home360.application.dto.response.SaveCityResponse;
import com.pragma.home360.application.services.CityService;
import com.pragma.home360.infrastructure.constants.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.CITY_BASE)
@RequiredArgsConstructor

public class CityController {
    private final CityService cityService;

    @Operation(summary = "Crear una ciudad"
            , description = "Este endpoint guarda una ciudad en la BD")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Ciudad creada exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SaveCityResponse.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de respuesta exitosa",
                                    value = """
                {
                  "id": 1,
                  "name": "City",
                  "description": "City description"
                }
                """
                            )
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })

    @PostMapping("/")
    public ResponseEntity<SaveCityResponse> save(@RequestBody SaveCityRequest saveCityRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cityService.save(saveCityRequest));
    }
    @Operation(summary = "Obtener lista de ciudades",
            description = "Este endpoint obtiene una lista de ciudades paginada")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de ciudades obtenida exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CityResponse.class)
                    )
            ),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })

    @GetMapping("/")
    public ResponseEntity<List<CityResponse>> getCities(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "15") Integer size,
            @RequestParam(value = "orderAsc", defaultValue = "false") boolean orderAsc) {
        return ResponseEntity.ok(cityService.getCities(page, size, orderAsc));
    }
}
