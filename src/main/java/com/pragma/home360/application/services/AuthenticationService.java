package com.pragma.home360.application.services;

import com.pragma.home360.application.dto.request.AuthenticationRequest;
import com.pragma.home360.application.dto.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
    boolean validateToken(String token);
    void logout(String email);
    AuthenticationResponse getAuthenticatedUser(String token);
    AuthenticationResponse refreshToken(String token);
}