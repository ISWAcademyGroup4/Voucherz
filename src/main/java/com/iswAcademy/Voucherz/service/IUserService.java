package com.iswAcademy.Voucherz.service;

import com.iswAcademy.Voucherz.domain.User;

import java.util.List;

public interface IUserService {

    public User createUser (User user);
    public boolean updateUser(String email , User user);
    public User findUserById(long id);
    public User findUser(String Email);
    public User findByToken(String token);
    public boolean updatePassword(String email, User user);
    public List<User> findAll(String name);
    public boolean isActive(User user, String email );
    public boolean updateRole(User user, String email);
}
