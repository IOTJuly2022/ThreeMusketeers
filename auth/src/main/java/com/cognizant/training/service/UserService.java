package com.cognizant.training.service;

import com.cognizant.training.exception.LoginException;
import com.cognizant.training.exception.RegisterException;
import com.cognizant.training.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void login(LoginRequest request) {
        if (request.getEmail().isBlank()) throw new LoginException("Email cannot be blank");
        if (request.getPassword().isBlank()) throw new LoginException("Password cannot be blank");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public void register() throws RegisterException {

    }
}
