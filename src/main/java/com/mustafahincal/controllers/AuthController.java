package com.mustafahincal.controllers;

import com.mustafahincal.business.abstracts.UserService;
import com.mustafahincal.core.utilities.results.DataResult;
import com.mustafahincal.core.utilities.results.ErrorResult;
import com.mustafahincal.core.utilities.results.Result;
import com.mustafahincal.core.utilities.results.SuccessDataResult;
import com.mustafahincal.entities.User;
import com.mustafahincal.requests.UserLoginRequest;
import com.mustafahincal.requests.UserRegisterRequest;
import com.mustafahincal.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/login")
    public DataResult<String> login(@RequestBody UserLoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        return new SuccessDataResult<String>(jwtToken, "Login successfull");

        /*
        User user = userService.getOneUserByUserName(loginRequest.getUserName());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setAccessToken("Bearer " + jwtToken);
        authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));
        authResponse.setUserId(user.getId());
        return authResponse;

         */
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserRegisterRequest registerRequest) {

        if (!userService.userExists(registerRequest.getEmail()).isSuccess()) {
            return new ErrorResult("Email already in use");
        }

        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole("user");
        userService.add(user);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(registerRequest.getEmail(), registerRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);


        return new SuccessDataResult<>(jwtToken, "User registered");
    }
}