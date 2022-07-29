package com.cognizant.training.service;

import com.cognizant.training.exception.LoginException;
import com.cognizant.training.exception.RegisterException;
import com.cognizant.training.request.LoginRequest;
import com.cognizant.training.security.UserDetailsImpl;

public interface IUserService {
    void login(LoginRequest request);
    void register() throws RegisterException;
}
