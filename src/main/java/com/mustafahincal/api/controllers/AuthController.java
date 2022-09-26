package com.mustafahincal.api.controllers;

import com.mustafahincal.business.abstracts.AuthService;
import com.mustafahincal.core.utilities.results.DataResult;
import com.mustafahincal.requests.UserLoginRequest;
import com.mustafahincal.requests.UserRegisterRequest;
import com.mustafahincal.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public DataResult<UserResponse> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        return this.authService.register(userRegisterRequest);
    }

    @PostMapping("/login")
    public DataResult<UserResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
        return this.authService.login(userLoginRequest);
    }
}
