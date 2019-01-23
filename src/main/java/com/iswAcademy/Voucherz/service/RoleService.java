package com.iswAcademy.Voucherz.service;

import com.iswAcademy.Voucherz.domain.Role;
import com.iswAcademy.Voucherz.domain.RoleName;

public interface RoleService {
    public Role createRole (Role role);
    public boolean updateRole(Long id, Role role);
    public Role findRole(RoleName roleName);

}
