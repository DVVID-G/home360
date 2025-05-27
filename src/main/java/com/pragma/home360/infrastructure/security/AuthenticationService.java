package com.pragma.home360.infrastructure.security;

import com.pragma.home360.application.dto.request.AuthenticationRequest;
import com.pragma.home360.application.dto.response.AuthenticationResponse;
import com.pragma.home360.domain.model.UserModel;
import com.pragma.home360.domain.ports.in.UserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserServicePort userServicePort;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        UserModel user = userServicePort.getUserByEmail(request.email());
        String jwtToken = jwtService.generateToken(new UserDetailsImpl(user));

        return new AuthenticationResponse(jwtToken);
    }
}