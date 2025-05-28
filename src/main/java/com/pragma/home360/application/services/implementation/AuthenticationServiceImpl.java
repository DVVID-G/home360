package com.pragma.home360.application.services.implementation;

import com.pragma.home360.application.dto.request.AuthenticationRequest;
import com.pragma.home360.application.dto.response.AuthenticationResponse;
import com.pragma.home360.application.services.AuthenticationService;
import com.pragma.home360.domain.model.AuthenticationModel;
import com.pragma.home360.domain.ports.in.AuthenticationServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationServicePort authenticationServicePort;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        AuthenticationModel authModel = AuthenticationModel.createForAuthentication(
                request.email(),
                request.password()
        );
        AuthenticationModel result = authenticationServicePort.authenticate(authModel);
        return new AuthenticationResponse(result.getToken());
    }

    @Override
    public boolean validateToken(String token) {
        return authenticationServicePort.validateToken(token);
    }

    @Override
    public void logout(String email) {
        authenticationServicePort.logout(email);
    }

    @Override
    public AuthenticationResponse getAuthenticatedUser(String token) {
        AuthenticationModel result = authenticationServicePort.getAuthenticatedUser(token);
        return new AuthenticationResponse(result.getToken());
    }

    @Override
    public AuthenticationResponse refreshToken(String token) {
        String newToken = authenticationServicePort.refreshToken(token);
        return new AuthenticationResponse(newToken);
    }
}