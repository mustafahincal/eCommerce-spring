package com.mustafahincal.business.abstracts;

import com.mustafahincal.core.utilities.results.DataResult;
import com.mustafahincal.core.utilities.results.Result;
import com.mustafahincal.entities.User;

import java.util.List;

public interface UserService {
    DataResult<List<User>> getAll();

    DataResult<User> findByEmail(String email);

    Result add(User user);

    Result delete(User user);

    Result update(User user);

    Result userExists(String email);

    DataResult<User> findById(int id);
}
