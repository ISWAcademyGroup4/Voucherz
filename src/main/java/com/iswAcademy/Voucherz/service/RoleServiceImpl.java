package com.iswAcademy.Voucherz.service;

import com.iswAcademy.Voucherz.dao.RoleDao;
import com.iswAcademy.Voucherz.domain.Role;
import com.iswAcademy.Voucherz.domain.RoleName;
import com.iswAcademy.Voucherz.exception.RequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;

    @Override
    public Role createRole(Role role){
        if(roleDao.findByName(role.getName())== null){
            return  roleDao.create(role);
        }

        return null;
    }
    @Override
    public boolean updateRole(Long id, Role role){
        Role existingRole = roleDao.findById(id);
        if(existingRole == null)
            throw new RequestException("Role not found");
        role.setId(id);
        return roleDao.update(role);


    }


    @Override
    public Role findRole(RoleName roleName) {

        return roleDao.findByName(roleName);
    }

}
