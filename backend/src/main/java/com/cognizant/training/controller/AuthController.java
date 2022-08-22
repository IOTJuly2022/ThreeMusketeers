package com.cognizant.training.controller;

import com.cognizant.training.model.Order;
import com.cognizant.training.model.User;
import com.cognizant.training.repository.OrderRepository;
import com.cognizant.training.repository.UserRepository;
import com.cognizant.training.request.LoginRequest;
import com.cognizant.training.request.RegisterRequest;
import com.cognizant.training.response.LoginResponse;
import com.cognizant.training.service.UserService;
import com.cognizant.training.util.JwtUtils;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    /**
     * The password encoder to use when encoding saving to the database.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping(path="/login")
    public LoginResponse userLogin(@RequestBody LoginRequest loginRequest) {
        // Will throw runtime errors if anything fails and responses handled by exception handler
        userService.login(loginRequest);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        LoginResponse response = new LoginResponse();
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setToken(jwtUtils.generateJwtToken(user));
        response.setId(user.getId());
        return response;
    }

    @PostMapping(path="/register")
    public ResponseEntity<Void> userRegister(@RequestBody RegisterRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setOrder(new Order());
        //orderRepository.saveAndFlush(user.getOrder());
        user = userRepository.saveAndFlush(user);

        return ResponseEntity.ok().body(null);
    }
}
