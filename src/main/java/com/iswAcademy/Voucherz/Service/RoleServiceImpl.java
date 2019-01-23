package com.iswAcademy.Voucherz.Service;

import com.iswAcademy.Voucherz.Dao.RoleDao;
import com.iswAcademy.Voucherz.Model.Role;
import com.iswAcademy.Voucherz.Model.RoleName;
import com.iswAcademy.Voucherz.exception.RequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

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
