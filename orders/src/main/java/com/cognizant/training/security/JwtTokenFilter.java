package com.cognizant.training.security;

import com.cognizant.training.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Filter applied to each request to extract the JWT token from the request headers if present and set the correct
 * security context authentication.
 *
 * A valid authorization header should look like the following
 *
 * Authorization = Bearer
 *
 * @author William Simpson
 */
public class JwtTokenFilter extends OncePerRequestFilter {

    /**
     * The authorization scheme used
     */
    public static final String AUTHORIZATION_SCHEME = "Bearer";

    /**
     * The header containing the authorization token
     */
    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            String token = extractTokenFromHeaders(request);
            if (token != null && jwtUtils.validate(token)) {
                // Token exists and is valid
                String id = jwtUtils.getIdFromJwtToken(token);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                id,
                                "",
                                List.of()
                        );

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid authorization token");
        }

        filterChain.doFilter(request, response);
    }

    protected String extractTokenFromHeaders(HttpServletRequest request) {
        String authHeader = request.getHeader(AUTHORIZATION_HEADER);

        if (authHeader == null) return null;

        if (authHeader.startsWith(AUTHORIZATION_SCHEME)) return null;

        return authHeader.substring(AUTHORIZATION_SCHEME.length() + 1);
    }
}
