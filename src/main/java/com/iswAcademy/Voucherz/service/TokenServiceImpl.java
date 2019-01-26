package com.iswAcademy.Voucherz.service;

import com.iswAcademy.Voucherz.dao.ITokenDao;
import com.iswAcademy.Voucherz.dao.IUserDao;
import com.iswAcademy.Voucherz.domain.PasswordResetToken;

import com.iswAcademy.Voucherz.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements ITokenService {

    @Autowired
    IUserDao userDao;

    @Autowired
    ITokenDao tokenDao;

    public PasswordResetToken createToken(PasswordResetToken token) {
        return tokenDao.create(token);
    }

    @Override
    public PasswordResetToken findByToken(String token) {
        return null;
    }

    @Override
    public User findUserByToken(String token) {
        return tokenDao.findUserByToken(token);
    }


}

