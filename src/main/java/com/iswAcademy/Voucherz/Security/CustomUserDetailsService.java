package com.iswAcademy.Voucherz.Security;

import com.iswAcademy.Voucherz.Dao.UserDao;
import com.iswAcademy.Voucherz.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String Email) throws UsernameNotFoundException {
//        let people login with their detail username= email its a method in spring boot

        User user = userDao.findByEmail(Email);
        if (user == null){
            throw new UsernameNotFoundException("Unregistered user:" + Email);
        }
        return UserPrincipal.create(user);

    }


    //this method is used by jwtauthenticationfilter

    @Transactional
    public UserDetails loadUserById(Long id){
        User user = userDao.findById(id);
        if (user == null){
            throw new UsernameNotFoundException("User not found id:" + id);
        }

        return UserPrincipal.create(user);
    }


}
