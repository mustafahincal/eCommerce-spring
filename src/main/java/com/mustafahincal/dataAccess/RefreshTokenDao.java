package com.mustafahincal.dataAccess;

import com.mustafahincal.entities.RefreshToken;
import com.mustafahincal.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RefreshTokenDao extends JpaRepository<RefreshToken, Integer> {
    RefreshToken findByUser(User user);

    @Query(nativeQuery = true, value = "SELECT * From refresh_token rt where rt.user_id=:userId order by  rt.expiry_date desc limit 1")
    RefreshToken findFirstByUserAndExpiryDateDesc(int userId);


}

