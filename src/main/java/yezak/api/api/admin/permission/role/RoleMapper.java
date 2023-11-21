package yezak.api.api.admin.permission.role;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import yezak.api.api.admin.permission.role.dto.GetRolesRes;
import yezak.api.api.admin.permission.role.dto.InsertRoleReq;
import yezak.api.api.admin.permission.role.dto.RoleCategoryRes;
import yezak.api.api.admin.permission.role.dto.UpdateRoleReq;

import java.util.List;

@Mapper

public interface RoleMapper {

    List<RoleCategoryRes> getRoleCategories(Long hospitalId);

    List<GetRolesRes> getRoles(@Param("id") Long id, @Param("hospitalId") Long hospitalId);

    void insertRole(InsertRoleReq insertRoleReq);

    Long findRoleCategoryId (Long id);

    String getKoName(String koName);

    int countUser(Long roleId);
    void deleteRole(Long id);

    void updateRole(UpdateRoleReq updateRoleReq);

    void insertDepth3(@Param("roleId")Long roleId, @Param("depth3NavigationId")Long depth3NavigationId);

}
