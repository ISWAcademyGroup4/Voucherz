package com.iswAcademy.Voucherz.dao;

import com.iswAcademy.Voucherz.domain.User;

public interface IUserDao extends IBaseDao<User> {
    User findByEmail(String Email);
    boolean isActive( User user, String email);
    boolean updateRole(User user, String email);
}
