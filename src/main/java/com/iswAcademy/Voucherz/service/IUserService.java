package com.iswAcademy.Voucherz.service;

import com.iswAcademy.Voucherz.domain.User;

public interface IUserService {

    public User createUser (User user);
    public boolean updateUser(Long id , User user);
    public User findUserById(long id);
    public User findUser(String Email);
    public User findByToken(String token);
    public boolean updatePassword(String email, User user);
}
