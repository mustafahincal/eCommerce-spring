package com.mustafahincal.business.concretes;

import com.mustafahincal.dataAccess.UserDao;
import com.mustafahincal.entities.User;
import com.mustafahincal.security.JwtUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsManager implements UserDetailsService {

    private UserDao userDao;

    public UserDetailsManager(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByEmail(username);

        return JwtUserDetails.create(user);
    }

    public UserDetails loadByUserId(int id) {
        User user = userDao.findById(id);
        return JwtUserDetails.create(user);
    }
}
