package com.iswAcademy.Voucherz.dao;


import com.iswAcademy.Voucherz.domain.Role;
import com.iswAcademy.Voucherz.domain.RoleName;


public interface RoleDao extends BaseDao<Role> {
    Role findByName(RoleName rolename);

}
