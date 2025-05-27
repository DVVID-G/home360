package com.pragma.home360.application.dto.request;

import java.time.LocalDate;

public record SaveUserRequest(String userIdentification,
                              String firstName,
                              String lastName,
                              String phoneNumber,
                              LocalDate dateOfBirth,
                              String email,
                              String password,
                              String address,
                              String role) {
}
