package com.iswAcademy.Voucherz.security;

import com.iswAcademy.Voucherz.dao.IUserDao;
import com.iswAcademy.Voucherz.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    IUserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String Email) throws UsernameNotFoundException {
        User user = userDao.findByEmail(Email);
        if (user == null){
            throw new UsernameNotFoundException("User not found with username or email:" + Email);
        }
        return UserPrincipal.create(user);

    }

    @Transactional
    public UserDetails loadUserById(Long id){
        User user = userDao.findById(id);
        if (user == null){
            throw new UsernameNotFoundException("User not found with id:" + id);
        }

        return UserPrincipal.create(user);
    }


}
