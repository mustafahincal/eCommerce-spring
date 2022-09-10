package com.mustafahincal.core.dataAccess;

import com.mustafahincal.core.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
