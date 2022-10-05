package com.mustafahincal.controllers;

import com.mustafahincal.business.abstracts.RefreshTokenService;
import com.mustafahincal.business.abstracts.UserService;
import com.mustafahincal.core.utilities.results.DataResult;
import com.mustafahincal.core.utilities.results.ErrorDataResult;
import com.mustafahincal.core.utilities.results.SuccessDataResult;
import com.mustafahincal.entities.RefreshToken;
import com.mustafahincal.entities.User;
import com.mustafahincal.requests.RefreshRequest;
import com.mustafahincal.requests.UserLoginRequest;
import com.mustafahincal.requests.UserRegisterRequest;
import com.mustafahincal.responses.AuthResponse;
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
    private RefreshTokenService refreshTokenService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, PasswordEncoder passwordEncoder, RefreshTokenService refreshTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.refreshTokenService = refreshTokenService;
    }


    @PostMapping("/login")
    public DataResult<AuthResponse> login(@RequestBody UserLoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);

        User user = userService.findByEmail(loginRequest.getEmail()).getData();

        AuthResponse response = new AuthResponse();
        response.setUserId(user.getUserId());
        response.setToken(jwtToken);
        response.setRefreshToken(refreshTokenService.createRefreshToken(user));

        return new SuccessDataResult<AuthResponse>(response, "Login successfull");

    }

    @PostMapping("/register")
    public DataResult<AuthResponse> register(@RequestBody UserRegisterRequest registerRequest) {

        if (!userService.userExists(registerRequest.getEmail()).isSuccess()) {
            return new ErrorDataResult<AuthResponse>("Email already in use");
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

        AuthResponse response = new AuthResponse();
        response.setUserId(user.getUserId());
        response.setToken(jwtToken);
        response.setRefreshToken(refreshTokenService.createRefreshToken(user));

        return new SuccessDataResult<AuthResponse>(response, "User registered");
    }

    @PostMapping("/refresh")
    public DataResult<AuthResponse> refresh(@RequestBody RefreshRequest refreshRequest) {
        User user = userService.findById(refreshRequest.getUserId()).getData();
        RefreshToken refreshToken = refreshTokenService.getByUser(user);
        if (refreshToken.getToken().equals(refreshRequest.getRefreshToken())
                && refreshTokenService.isRefreshTokenExpired(refreshToken)) {


            String jwtToken = jwtTokenProvider.generateJwtTokenByUserName(user.getUserId());
            AuthResponse response = new AuthResponse();
            response.setUserId(user.getUserId());
            response.setToken(jwtToken);
            response.setRefreshToken(refreshTokenService.createRefreshToken(user));
            return new SuccessDataResult<AuthResponse>(response, "Refresh token is valid.");
        } else {
            return new ErrorDataResult<AuthResponse>("Refresh token is not valid.");
        }
    }
}
