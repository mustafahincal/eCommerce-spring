package com.mustafahincal.requests;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String email;
    private String password;
}
