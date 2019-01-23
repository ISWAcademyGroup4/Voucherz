package com.iswAcademy.Voucherz.dao.impl;

import com.iswAcademy.Voucherz.dao.RoleDao;
import com.iswAcademy.Voucherz.domain.Role;
import com.iswAcademy.Voucherz.domain.RoleName;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    @Override
    public Role findByName(RoleName rolename) {
        return null;
    }

    @Override
    public Role create(Role Model) {
        return null;
    }

    @Override
    public boolean update(Role model) {
        return false;
    }

    @Override
    public Role find(String email) {
        return null;
    }

    @Override
    public Role findById(long id) {
        return null;
    }

    @Override
    public List<Role> ReadAll() {
        return null;
    }

    @Override
    public boolean delete(Role model) {
        return false;
    }
}
