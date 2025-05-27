package com.pragma.home360.infrastructure.endpoints.rest;


import com.pragma.home360.application.dto.request.SaveUserRequest;
import com.pragma.home360.application.dto.response.SaveUserResponse;
import com.pragma.home360.application.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "User API")
public class UserController {
    private final UserService userService;

    @PostMapping("/")
    public ResponseEntity<SaveUserResponse> save(@RequestBody SaveUserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userRequest));
    }


}
