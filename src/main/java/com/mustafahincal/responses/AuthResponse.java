package com.mustafahincal.responses;

import lombok.Data;

@Data
public class AuthResponse {
    String token;
    String refreshToken;
    int userId;
}
