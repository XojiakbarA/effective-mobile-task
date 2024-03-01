package org.example.effectivemobiletask.controller;

import jakarta.validation.Valid;
import org.example.effectivemobiletask.dto.request.LoginRequest;
import org.example.effectivemobiletask.dto.request.RegisterRequest;
import org.example.effectivemobiletask.dto.response.AuthResponse;
import org.example.effectivemobiletask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        String token = userService.login(request);

        return new AuthResponse(token, HttpStatus.OK.name());
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse register(@Valid @RequestBody RegisterRequest request) {
        userService.register(request);

        return new AuthResponse("Registration completed successfully. Please login");
    }
}
