package com.cognizant.training.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Default Authentication Entry Point for the service. Since our service is purely a REST API we rejects all requests
 * and responds with HTTP 401 (Unauthorized).
 *
 * @author William Simpson
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException {
        response.sendError(HttpStatus.UNAUTHORIZED.value(), "Authentication required");
    }
}
