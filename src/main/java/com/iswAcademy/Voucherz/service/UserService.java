package com.iswAcademy.Voucherz.service;

import com.iswAcademy.Voucherz.domain.User;

public interface UserService {

    public User createUser (User user);
    public boolean updateUser(Long id , User user);
    public User findUser(String Email);

}
