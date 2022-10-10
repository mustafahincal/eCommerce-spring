package com.mustafahincal.business.concretes;

import com.mustafahincal.business.abstracts.RefreshTokenService;
import com.mustafahincal.business.abstracts.UserService;
import com.mustafahincal.dataAccess.RefreshTokenDao;
import com.mustafahincal.entities.RefreshToken;
import com.mustafahincal.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class RefreshTokenManager implements RefreshTokenService {
    @Value("${refresh.token.expires.in}")
    Long expiredSeconds;
    private RefreshTokenDao refreshTokenDao;

    public RefreshTokenManager(RefreshTokenDao refreshTokenDao, UserService userService) {
        this.refreshTokenDao = refreshTokenDao;
    }

    @Override
    public boolean isRefreshTokenExpired(RefreshToken token) {
        return !token.getExpiryDate().before(new Date());
    }

    public String createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Date.from(Instant.now().plusSeconds(expiredSeconds)));
        this.refreshTokenDao.save(refreshToken);
        return refreshToken.getToken();
    }

    @Override
    public RefreshToken getByUser(User user) {
        return this.refreshTokenDao.findFirstByUserAndExpiryDateDesc(user.getId());
    }
}