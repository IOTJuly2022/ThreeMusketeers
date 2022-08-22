package com.cognizant.training.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Base64;

@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    /**
     * The JWT secret to base64 encode and use for signing
     */
    @Value("${cognizant.jwt.secret}")
    private String secret;

    /**
     * Parser used for parsing claims and validating tokens
     */
    private JwtParser parser;

    @PostConstruct
    public void init() {
        Key key = Keys.hmacShaKeyFor(Base64.getEncoder().encode(secret.getBytes()));
        parser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();
    }

    public boolean validate(String token) {
        try {
            parser.parseClaimsJws(token);
            return true;
        }
        catch (SecurityException ex)
        {
            logger.error("Invalid JWT signature: {}", ex.getMessage());
        }
        catch (MalformedJwtException ex)
        {
            logger.error("Invalid JWT token: {}", ex.getMessage());
        }
        catch (ExpiredJwtException ex)
        {
            logger.error("JWT token is expired: {}", ex.getMessage());
        }
        catch (UnsupportedJwtException ex)
        {
            logger.error("JWT token is unsupported: {}", ex.getMessage());
        }
        catch (IllegalArgumentException ex)
        {
            logger.error("JWT claims string is empty: {}", ex.getMessage());
        }

        return false;
    }

    public String getIdFromJwtToken(String token) {
        return parser.parseClaimsJws(token).getBody().getSubject();
    }
}
