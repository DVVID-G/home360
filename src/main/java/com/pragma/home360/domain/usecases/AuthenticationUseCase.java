package com.pragma.home360.domain.usecases;

import com.pragma.home360.domain.exceptions.InvalidCredentialsException;
import com.pragma.home360.domain.exceptions.InvalidTokenException;
import com.pragma.home360.domain.model.AuthenticationModel;
import com.pragma.home360.domain.ports.in.AuthenticationServicePort;
import com.pragma.home360.domain.ports.out.AuthenticationPersistencePort;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
public class AuthenticationUseCase implements AuthenticationServicePort {

    private final AuthenticationPersistencePort authenticationPersistencePort;

    @Override
    public AuthenticationModel authenticate(AuthenticationModel authenticationModel) {
        if (!authenticationPersistencePort.validateCredentials(authenticationModel)) {
            throw new InvalidCredentialsException("Credenciales inválidas");
        }

        String token = authenticationPersistencePort.generateToken(authenticationModel.getEmail());
        authenticationPersistencePort.saveToken(authenticationModel.getEmail(), token);

        return AuthenticationModel.builder()
                .email(authenticationModel.getEmail())
                .token(token)
                .build();
    }

    @Override
    public boolean validateToken(String token) {
        return authenticationPersistencePort.isTokenValid(token);
    }

    @Override
    public void logout(String email) {
        authenticationPersistencePort.invalidateTokens(email);
    }

    @Override
    public AuthenticationModel getAuthenticatedUser(String token) {
        if (!validateToken(token)) {
            throw new InvalidTokenException("Token inválido");
        }

        String email = authenticationPersistencePort.getEmailFromToken(token);
        return AuthenticationModel.builder()
                .email(email)
                .token(token)
                .build();
    }

    @Override
    public String refreshToken(String token) {
        if (!validateToken(token)) {
            throw new InvalidTokenException("Token inválido");
        }

        String email = authenticationPersistencePort.getEmailFromToken(token);
        String newToken = authenticationPersistencePort.generateToken(email);
        authenticationPersistencePort.saveToken(email, newToken);
        return newToken;
    }
}