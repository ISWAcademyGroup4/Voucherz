package com.iswAcademy.Voucherz.Dao;

import com.iswAcademy.Voucherz.Model.User;

public interface UserDao extends BaseDao<User>{
    User findByEmail(String Email);
}
