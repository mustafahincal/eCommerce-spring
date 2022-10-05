package com.mustafahincal.business.abstracts;

import com.mustafahincal.entities.RefreshToken;
import com.mustafahincal.entities.User;

public interface RefreshTokenService {
    boolean isRefreshTokenExpired(RefreshToken token);

    String createRefreshToken(User user);

    RefreshToken getByUser(User user);
}
