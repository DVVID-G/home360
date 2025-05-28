package com.pragma.home360.application.dto.response;

import java.time.LocalDate;

public record UserResponse(Long userId,
                           String userIdentification,
                           String firstName,
                           String lastName,
                           String phoneNumber,
                           LocalDate dateOfBirth,
                           String email,
                           String role
) {
}
