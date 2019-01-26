package com.iswAcademy.Voucherz.dao;

import com.iswAcademy.Voucherz.domain.PasswordResetToken;
import com.iswAcademy.Voucherz.domain.User;

public interface ITokenDao extends IBaseDao<PasswordResetToken> {
    public User findUserByToken(String token);
}
