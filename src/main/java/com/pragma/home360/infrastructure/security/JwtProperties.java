package com.pragma.home360.infrastructure.security;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private final String secretKey;
    private final long expiration;
    private final String issuer;

    public JwtProperties(String secretKey, long expiration, String issuer) {
        this.secretKey = secretKey;
        this.expiration = expiration;
        this.issuer = issuer;
    }

}