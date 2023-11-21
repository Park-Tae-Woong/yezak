package yezak.api.api.admin.permission.role;

import yezak.api.api.admin.permission.role.dto.InsertRoleReq;
import yezak.api.api.admin.permission.role.dto.UpdateRoleReq;
import yezak.api.global.common.ResultResponse;

public interface RoleService {

    ResultResponse<?> getRoleCategories();

    ResultResponse<?> getRoles(Long id);

    ResultResponse<?> insertRole(InsertRoleReq insertRoleReq);

    ResultResponse<?> deleteRole(Long id);

    ResultResponse<?> updateRole(UpdateRoleReq updateRoleReq);

}
