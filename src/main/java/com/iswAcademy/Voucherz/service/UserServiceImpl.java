package com.iswAcademy.Voucherz.service;


import com.iswAcademy.Voucherz.dao.IUserDao;
import com.iswAcademy.Voucherz.domain.User;
import com.iswAcademy.Voucherz.exception.RequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public boolean updateUser(Long id, User user){
        User existingUser = userDao.findById(id);
        if(existingUser == null)
            throw new RequestException("User not found");
        user.setId(id);
        return userDao.update(user);
    }

    @Override
    public boolean updatePassword(User user){
        return userDao.update(user);
    }

    @Override
    public User findUser(String Email) {
        return userDao.find(Email);
    }

    public User findByToken(String token) {
        return userDao.findUserByToken(token);
    }

}
