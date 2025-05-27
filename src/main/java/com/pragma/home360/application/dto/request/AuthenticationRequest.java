package com.pragma.home360.application.dto.request;

public record AuthenticationRequest(    String email,
                                        String password
) {
}
