package com.mustafahincal.dataAccess;

import com.mustafahincal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
    User findByEmail(String username);

    User findById(int id);
}
