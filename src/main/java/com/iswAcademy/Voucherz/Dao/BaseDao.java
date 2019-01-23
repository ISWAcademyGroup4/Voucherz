package com.iswAcademy.Voucherz.Dao;

import java.util.List;

public interface BaseDao<T> {
    public T create(T Model);

    public boolean update(T model);

    public T find(String email);

    public T findById(long id);

    public List<T> ReadAll();

    public boolean delete(T model);
}
