package com.pragma.home360.domain.ports.out;

import com.pragma.home360.domain.model.AuthenticationModel;

public interface AuthenticationPersistencePort {
    boolean validateCredentials(AuthenticationModel authenticationModel);
    void saveToken(String email, String token);
    void invalidateTokens(String email);
    boolean isTokenValid(String token);
    String getEmailFromToken(String token);
    String generateToken(String email);
}