
package com.pragma.home360.infrastructure.security;

import com.pragma.home360.infrastructure.constants.ApiConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(this::configureAuthorization)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    private void configureAuthorization(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth) {
        // Public endpoints
        configurePublicEndpoints(auth);

        // User endpoints
        configureUserEndpoints(auth);

        // Category endpoints
        configureCategoryEndpoints(auth);

        // Location-related endpoints (admin-only CRUD)
        configureAdminOnlyEndpoints(auth, ApiConstants.LOCATION_PATH);
        configureAdminOnlyEndpoints(auth, ApiConstants.DEPARTMENT_PATH);
        configureAdminOnlyEndpoints(auth, ApiConstants.CITY_PATH);

        // House endpoints
        configureHouseEndpoints(auth);

        // Search endpoints
        configureSearchEndpoints(auth);

        // All other requests require authentication
        auth.anyRequest().authenticated();
    }

    private void configurePublicEndpoints(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth) {
        auth.requestMatchers(ApiConstants.AUTH_PATH).permitAll()
                .requestMatchers(ApiConstants.SWAGGER_UI, ApiConstants.API_DOCS).permitAll();
    }

    private void configureUserEndpoints(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth) {
        auth.requestMatchers(HttpMethod.POST, ApiConstants.USERS_BASE + "/").permitAll()
                .requestMatchers(HttpMethod.GET, ApiConstants.USERS_PATH).hasAnyRole(ApiConstants.ROLE_ADMIN, ApiConstants.ROLE_VENDEDOR)
                .requestMatchers(HttpMethod.PUT, ApiConstants.USERS_PATH).hasAnyRole(ApiConstants.ROLE_ADMIN, ApiConstants.ROLE_VENDEDOR)
                .requestMatchers(HttpMethod.DELETE, ApiConstants.USERS_PATH).hasRole(ApiConstants.ROLE_ADMIN);
    }

    private void configureCategoryEndpoints(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth) {
        configureAdminCrudWithPublicRead(auth, ApiConstants.CATEGORY_PATH, false); // Categories require authentication to read
    }

    private void configureHouseEndpoints(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth) {
        auth.requestMatchers(HttpMethod.POST, ApiConstants.HOUSE_PATH).hasRole(ApiConstants.ROLE_VENDEDOR)
                .requestMatchers(HttpMethod.PUT, ApiConstants.HOUSE_PATH).hasRole(ApiConstants.ROLE_VENDEDOR)
                .requestMatchers(HttpMethod.DELETE, ApiConstants.HOUSE_PATH).hasAnyRole(ApiConstants.ROLE_ADMIN, ApiConstants.ROLE_VENDEDOR)
                .requestMatchers(HttpMethod.GET, ApiConstants.HOUSE_PATH).permitAll();
    }

    private void configureSearchEndpoints(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth) {
        auth.requestMatchers(ApiConstants.HOUSE_SEARCH).permitAll();
    }

    /**
     * Configures endpoints where only ADMIN can create/update/delete, but reading is public or authenticated
     * @param auth The authorization registry
     * @param path The endpoint path
     * @param publicRead If true, GET requests are public; if false, GET requests require authentication
     */
    private void configureAdminCrudWithPublicRead(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth,
                                                  String path, boolean publicRead) {
        auth.requestMatchers(HttpMethod.POST, path).hasRole(ApiConstants.ROLE_ADMIN)
                .requestMatchers(HttpMethod.PUT, path).hasRole(ApiConstants.ROLE_ADMIN)
                .requestMatchers(HttpMethod.DELETE, path).hasRole(ApiConstants.ROLE_ADMIN);

        if (publicRead) {
            auth.requestMatchers(HttpMethod.GET, path).permitAll();
        } else {
            auth.requestMatchers(HttpMethod.GET, path).authenticated();
        }
    }

    /**
     * Configures endpoints where only ADMIN can perform any operation except GET (which is public)
     */
    private void configureAdminOnlyEndpoints(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry auth,
                                             String path) {
        configureAdminCrudWithPublicRead(auth, path, true);
    }
}