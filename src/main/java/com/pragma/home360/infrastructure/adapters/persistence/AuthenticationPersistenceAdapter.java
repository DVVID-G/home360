package com.pragma.home360.infrastructure.adapters.persistence;

import com.pragma.home360.domain.model.AuthenticationModel;
import com.pragma.home360.domain.model.UserModel;
import com.pragma.home360.domain.ports.out.AuthenticationPersistencePort;
import com.pragma.home360.infrastructure.security.JwtService;
import com.pragma.home360.infrastructure.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AuthenticationPersistenceAdapter implements AuthenticationPersistencePort {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Override
    public boolean validateCredentials(AuthenticationModel authenticationModel) {
        try {
            log.debug("Attempting to authenticate user: {}", authenticationModel.getEmail());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationModel.getEmail(),
                            authenticationModel.getPassword()
                    )
            );
            log.debug("Authentication result for user {}: {}", authenticationModel.getEmail(), authentication.isAuthenticated());
            return authentication.isAuthenticated();
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public String generateToken(String email) {  // Implementación del método faltante
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return jwtService.generateToken(userDetails);
    }


    @Override
    public void saveToken(String email, String token) {
        // En JWT stateless no necesitamos guardar el token
    }

    @Override
    public void invalidateTokens(String email) {
        // En JWT stateless no necesitamos invalidar tokens
    }

    @Override
    public boolean isTokenValid(String token) {
        return token != null && jwtService.isTokenValid(token);
    }

    @Override
    public String getEmailFromToken(String token) {
        return jwtService.extractUsername(token);
    }
}