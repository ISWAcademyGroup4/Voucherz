package com.iswAcademy.Voucherz.dao;

import com.iswAcademy.Voucherz.domain.Page;

import java.util.List;

public interface IBaseDao<T> {
    public T create(T Model);

    public boolean update(T model);

    public T find(String token);

    public T findById(long id);

    public Page<T> findAll();

    public boolean delete(T model);

    public T findUserByToken(String token);

    public boolean updatePassword(String email,T model);

}