package com.pragma.home360.infrastructure.endpoints.rest;

import com.pragma.home360.application.dto.request.SaveDeparmentRequest;
import com.pragma.home360.application.dto.response.DeparmentResponse;
import com.pragma.home360.application.dto.response.SaveDeparmentResponse;
import com.pragma.home360.application.services.DeparmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Deparment")
@RequiredArgsConstructor
@Tag(name = "Deparment", description = "Deparment API")

public class DeparmentController {
    private final DeparmentService deparmentService;

    @PostMapping("/")
    public ResponseEntity<SaveDeparmentResponse> save(@RequestBody SaveDeparmentRequest saveDeparmentRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(deparmentService.save(saveDeparmentRequest));
    }

    @GetMapping("")
    public ResponseEntity<List<DeparmentResponse>> getDeparments(@RequestParam Integer page,
                                                                 @RequestParam Integer size,
                                                                 @RequestParam boolean orderAsc) {
        return ResponseEntity.ok(deparmentService.getDeparments(page, size, orderAsc));
    }
}
