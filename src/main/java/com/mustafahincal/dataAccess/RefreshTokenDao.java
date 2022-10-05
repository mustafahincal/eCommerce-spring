package com.mustafahincal.dataAccess;

import com.mustafahincal.entities.RefreshToken;
import com.mustafahincal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenDao extends JpaRepository<RefreshToken, Integer> {
    RefreshToken findByUser(User user);
}
