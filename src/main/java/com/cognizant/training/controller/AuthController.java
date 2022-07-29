package com.cognizant.training.controller;

import com.cognizant.training.model.User;
import com.cognizant.training.repository.UserRepository;
import com.cognizant.training.request.LoginRequest;
import com.cognizant.training.security.UserDetailsImpl;
import com.cognizant.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@Controller
@RequestMapping(path = "/v1")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/login")
    public @ResponseBody
    String userLogin(@RequestBody LoginRequest loginRequest) {
        userService.login(loginRequest);
        Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return obj.toString();
    }
}
