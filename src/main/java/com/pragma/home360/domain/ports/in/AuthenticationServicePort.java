package com.pragma.home360.domain.ports.in;

import com.pragma.home360.domain.model.AuthenticationModel;

public interface AuthenticationServicePort {

    AuthenticationModel authenticate(AuthenticationModel authenticationModel);
    boolean validateToken(String token);
    void logout(String email);
    AuthenticationModel getAuthenticatedUser(String token);
    String refreshToken(String token);
}

