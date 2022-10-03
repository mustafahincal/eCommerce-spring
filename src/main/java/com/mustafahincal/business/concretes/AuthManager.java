package com.mustafahincal.business.concretes;

import com.mustafahincal.business.abstracts.AuthService;
import com.mustafahincal.business.abstracts.UserService;
import com.mustafahincal.core.utilities.results.DataResult;
import com.mustafahincal.core.utilities.results.ErrorDataResult;
import com.mustafahincal.core.utilities.results.SuccessDataResult;
import com.mustafahincal.entities.User;
import com.mustafahincal.requests.UserLoginRequest;
import com.mustafahincal.requests.UserRegisterRequest;
import com.mustafahincal.responses.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthManager implements AuthService {

    private UserService userService;

    @Autowired
    public AuthManager(UserService userService) {
        this.userService = userService;
    }

    @Override
    public DataResult<UserResponse> register(UserRegisterRequest userRegisterRequest) {

        var control = this.userService.userExists(userRegisterRequest.getEmail());
        if (!control.isSuccess()) {
            return new ErrorDataResult<UserResponse>(control.getMessage());
        }
        User userToAdd = new User();
        userToAdd.setEmail(userRegisterRequest.getEmail());
        userToAdd.setPassword(userRegisterRequest.getPassword());
        userToAdd.setFirstName(userRegisterRequest.getFirstName());
        userToAdd.setLastName(userRegisterRequest.getLastName());
        userToAdd.setRole("user");
        
        this.userService.add(userToAdd);

        var userInfo = new UserResponse(userToAdd);

        return new SuccessDataResult<UserResponse>(userInfo, "User added");
    }

    public DataResult<UserResponse> login(UserLoginRequest userLoginRequest) {
        var userToCheck = this.userService.findByEmail(userLoginRequest.getEmail());

        if (userToCheck.getData() == null) {
            return new ErrorDataResult<UserResponse>("User doesn't exists");
        }

        if (!userToCheck.getData().getPassword().equals(userLoginRequest.getPassword())) {
            return new ErrorDataResult<UserResponse>("Wrong Password");
        }
        var userInfo = new UserResponse(userToCheck.getData());
        return new SuccessDataResult<UserResponse>(userInfo, "Login successful");
    }


}
