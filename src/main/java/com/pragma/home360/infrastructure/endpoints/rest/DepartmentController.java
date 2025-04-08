package com.pragma.home360.infrastructure.endpoints.rest;

import com.pragma.home360.application.dto.request.SaveDepartmentRequest;
import com.pragma.home360.application.dto.response.DepartmentResponse;
import com.pragma.home360.application.dto.response.SaveDepartmentResponse;
import com.pragma.home360.application.services.DepartmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Department")
@RequiredArgsConstructor
@Tag(name = "Department", description = "Department API")

public class DepartmentController {
    private final DepartmentService departmentService;

    @PostMapping("/")
    public ResponseEntity<SaveDepartmentResponse> save(@RequestBody SaveDepartmentRequest saveDepartmentRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.save(saveDepartmentRequest));
    }

    @GetMapping("")
    public ResponseEntity<List<DepartmentResponse>> getDepartments(@RequestParam Integer page,
                                                                  @RequestParam Integer size,
                                                                  @RequestParam boolean orderAsc) {
        return ResponseEntity.ok(departmentService.getDepartments(page, size, orderAsc));
    }
}
