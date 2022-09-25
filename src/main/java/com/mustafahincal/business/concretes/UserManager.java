package com.mustafahincal.business.concretes;

import com.mustafahincal.business.abstracts.UserService;
import com.mustafahincal.core.dataAccess.UserDao;
import com.mustafahincal.core.entities.User;
import com.mustafahincal.core.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager implements UserService {
    private UserDao userDao;

    @Autowired
    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public DataResult<List<User>> getAll() {
        return new SuccessDataResult<List<User>>(this.userDao.findAll(), "Kullanıcılar getirildi");
    }

    @Override
    public DataResult<User> findByEmail(String email) {
        return new SuccessDataResult<User>(this.userDao.findByEmail((email)), "Kullanıcı getirildi");
    }

    @Override
    public Result add(User user) {
        this.userDao.save(user);
        return new SuccessResult("Kullanıcı eklendi");
    }

    @Override
    public Result delete(User user) {
        this.userDao.save(user);
        return new SuccessResult("Kullanıcı güncellendi");
    }

    @Override
    public Result update(User user) {
        this.userDao.delete(user);
        return new SuccessResult("Kullanıcı silindi");
    }

    public Result userExists(String email) {
        var userToCheck = this.userDao.findByEmail(email);
        if (userToCheck == null) {
            return new SuccessResult();
        }
        return new ErrorResult("Kullanıcı mevcut");
    }
}
