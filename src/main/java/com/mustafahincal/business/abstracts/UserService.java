package com.mustafahincal.business.abstracts;

import com.mustafahincal.core.entities.User;
import com.mustafahincal.core.utilities.results.DataResult;
import com.mustafahincal.core.utilities.results.Result;

import java.util.List;

public interface UserService {
    DataResult<List<User>> getAll();

    DataResult<User> findByEmail(String email);

    Result add(User user);

    Result delete(User user);

    Result update(User user);
}
