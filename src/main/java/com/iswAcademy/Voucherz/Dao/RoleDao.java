package com.iswAcademy.Voucherz.Dao;


import com.iswAcademy.Voucherz.Model.Role;
import com.iswAcademy.Voucherz.Model.RoleName;
import org.springframework.stereotype.Repository;


public interface RoleDao extends BaseDao<Role> {
    Role findByName(RoleName rolename);

}
