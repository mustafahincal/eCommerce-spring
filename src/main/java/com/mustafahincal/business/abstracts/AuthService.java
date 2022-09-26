package com.mustafahincal.business.abstracts;

import com.mustafahincal.core.utilities.results.DataResult;
import com.mustafahincal.requests.UserLoginRequest;
import com.mustafahincal.requests.UserRegisterRequest;
import com.mustafahincal.responses.UserResponse;

public interface AuthService {
    DataResult<UserResponse> register(UserRegisterRequest userRegisterRequest);

    DataResult<UserResponse> login(UserLoginRequest userLoginRequest);
}
