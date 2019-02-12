package com.iswAcademy.Voucherz.service;


import com.iswAcademy.Voucherz.dao.IUserDao;
import com.iswAcademy.Voucherz.domain.User;
import com.iswAcademy.Voucherz.exception.RequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserDao userDao;

    @Override
    public User createUser(User user){
        if(userDao.findByEmail(user.getEmail())== null){
            return  userDao.create(user);
        }

        return null;
    }
    @Override
    public boolean updateUser(String email, User user){
        User existingUser = userDao.findByEmail(email);
        if(existingUser == null)
            throw new RequestException("User not found");
        user.setEmail(email);
        return userDao.update(user);
    }

    @Override
    public boolean updatePassword(String email,User user){
        return userDao.updatePassword(email,user);
    }

    @Override
    public List<User> findAll(String name) {
        return userDao.findAll(name);
    }

    @Override
    public User findUserById(long id) {
        return userDao.findById(id);
    }

    @Override
    public User findUser(String Email) {
        return userDao.find(Email);
    }

    public User findByToken(String token) {
        return userDao.findUserByToken(token);
    }

    public boolean isActive(User user, String email ) {
        userDao.isActive(user, email);
        return true;
    }

    public boolean updateRole(User user, String email) {
        userDao.updateRole(user, email);
        return true;
    }

}
