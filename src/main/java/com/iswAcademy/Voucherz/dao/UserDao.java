package com.iswAcademy.Voucherz.dao;

import com.iswAcademy.Voucherz.domain.User;

public interface UserDao extends BaseDao<User>{
    User findByEmail(String Email);
}
