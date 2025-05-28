package com.pragma.home360.domain.model;

public class AuthenticationModel {
    private final String email;
    private final String password;
    private final String token;

    private AuthenticationModel(String email, String password, String token) {
        this.email = email;
        this.password = password;
        this.token = token;
    }

    // Getters
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    // Builder
    public static class Builder {
        private String email;
        private String password;
        private String token;

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public AuthenticationModel build() {
            return new AuthenticationModel(email, password, token);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    // Factory methods
    public static AuthenticationModel createForAuthentication(String email, String password) {
        return new AuthenticationModel(email, password, null);
    }

    public static AuthenticationModel createWithToken(String email, String token) {
        return new AuthenticationModel(email, null, token);
    }
}