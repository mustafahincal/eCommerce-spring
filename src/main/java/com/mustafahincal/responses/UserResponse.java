package com.mustafahincal.responses;

import com.mustafahincal.entities.User;
import lombok.Data;

@Data
public class UserResponse {
    private int userId;
    private String email;
    private String firstName;
    private String lastName;
    private String role;

    public UserResponse(User user) {
        this.userId = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.role = user.getRole();
    }
}
