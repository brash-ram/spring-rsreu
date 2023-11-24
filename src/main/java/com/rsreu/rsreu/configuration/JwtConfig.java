package com.rsreu.rsreu.configuration;

public record JwtConfig(
    String secret,
    Long expiration,
    String headerName
) {
}
