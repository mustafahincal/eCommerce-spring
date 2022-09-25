package com.mustafahincal.business.abstracts;

import com.mustafahincal.core.entities.User;
import com.mustafahincal.core.utilities.results.DataResult;
import com.mustafahincal.requests.UserLoginRequest;
import com.mustafahincal.requests.UserRegisterRequest;

public interface AuthService {
    DataResult<User> register(UserRegisterRequest userRegisterRequest);

    DataResult<User> login(UserLoginRequest userLoginRequest);
}
