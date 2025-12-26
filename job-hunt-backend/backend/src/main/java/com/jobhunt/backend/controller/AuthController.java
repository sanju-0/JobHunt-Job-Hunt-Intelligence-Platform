package com.jobhunt.backend.controller;

import com.jobhunt.backend.dto.LoginRequest;
import com.jobhunt.backend.dto.SignupRequest;
import com.jobhunt.backend.model.User;
import com.jobhunt.backend.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public User signup(@RequestBody SignupRequest request) {
        return authService.signup(request);
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
