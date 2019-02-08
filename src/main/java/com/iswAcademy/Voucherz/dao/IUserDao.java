package com.iswAcademy.Voucherz.dao;

import com.iswAcademy.Voucherz.domain.User;

public interface IUserDao extends IBaseDao<User> {
    User findByEmail(String Email);
    Boolean isActive(boolean active, String email);
}
