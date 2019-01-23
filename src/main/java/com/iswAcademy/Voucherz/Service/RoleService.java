package com.iswAcademy.Voucherz.Service;

import com.iswAcademy.Voucherz.Model.Role;
import com.iswAcademy.Voucherz.Model.RoleName;

public interface RoleService {
    public Role createRole (Role role);
    public boolean updateRole(Long id, Role role);
    public Role findRole(RoleName roleName);

}
