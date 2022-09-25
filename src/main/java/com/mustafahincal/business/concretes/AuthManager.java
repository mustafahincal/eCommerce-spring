package com.mustafahincal.business.concretes;

import com.mustafahincal.business.abstracts.AuthService;
import com.mustafahincal.business.abstracts.UserService;
import com.mustafahincal.core.entities.User;
import com.mustafahincal.core.utilities.results.DataResult;
import com.mustafahincal.core.utilities.results.ErrorDataResult;
import com.mustafahincal.core.utilities.results.SuccessDataResult;
import com.mustafahincal.requests.UserLoginRequest;
import com.mustafahincal.requests.UserRegisterRequest;
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
    public DataResult<User> register(UserRegisterRequest userRegisterRequest) {

        var control = this.userService.userExists(userRegisterRequest.getEmail());
        if (!control.isSuccess()) {
            return new ErrorDataResult<User>(control.getMessage());
        }
        User userToAdd = new User();
        userToAdd.setEmail(userRegisterRequest.getEmail());
        userToAdd.setPassword(userRegisterRequest.getPassword());
        userToAdd.setFirstName(userRegisterRequest.getFirstName());
        userToAdd.setLastName(userRegisterRequest.getLastName());
        this.userService.add(userToAdd);


        return new SuccessDataResult<User>(userToAdd, "User added");
    }

    public DataResult<User> login(UserLoginRequest userLoginRequest) {
        var userToCheck = this.userService.findByEmail(userLoginRequest.getEmail());

        if (userToCheck.getData() == null) {
            return new ErrorDataResult<User>("User doesn't exists");
        }

        if (!userToCheck.getData().getPassword().equals(userLoginRequest.getPassword())) {
            return new ErrorDataResult<User>("Wrong Password");
        }

        return new SuccessDataResult<>(userToCheck.getData(), "Login successful");
    }


}
