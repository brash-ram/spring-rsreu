package com.rsreu.rsreu.security.jwt;

import com.rsreu.bestProject.config.ApplicationConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    private final ApplicationConfig config;

    public String getJwtFromHeader(HttpServletRequest request) {
        return request.getHeader(config.jwt().headerName());
    }

    public String getEmailFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(config.jwt().secret()).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(config.jwt().secret()).parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            logger.error("Generate JWT token is failed: {}", e.getMessage());
        }

        return false;
    }

    public String generateTokenFromEmail(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, config.jwt().secret())
                .compact();
    }
}
