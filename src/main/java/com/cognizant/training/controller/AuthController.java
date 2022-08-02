package com.cognizant.training.controller;

import com.cognizant.training.model.User;
import com.cognizant.training.repository.UserRepository;
import com.cognizant.training.request.LoginRequest;
import com.cognizant.training.response.LoginResponse;
import com.cognizant.training.service.UserService;
import com.cognizant.training.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/v1")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping(path="/login")
    public @ResponseBody
    LoginResponse userLogin(@RequestBody LoginRequest loginRequest) {
        // Will throw runtime errors if anything fails and responses handled by exception handler
        userService.login(loginRequest);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        LoginResponse response = new LoginResponse();
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setToken(jwtUtils.generateJwtToken(user));

        return response;
    }
}
