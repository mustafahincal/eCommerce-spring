package com.mustafahincal.responses;

import com.mustafahincal.core.entities.User;
import lombok.Data;

@Data
public class UserResponse {
    private int userId;
    private String email;
    private String firstName;
    private String lastName;

    public UserResponse(User user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }
}
