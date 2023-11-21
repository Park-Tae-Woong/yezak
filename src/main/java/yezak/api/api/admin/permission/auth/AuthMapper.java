package yezak.api.api.admin.permission.auth;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import yezak.api.api.admin.permission.auth.dto.*;
import yezak.api.api.admin.permission.role.dto.RoleCategoryRes;

import java.util.List;

@Mapper
public interface AuthMapper {

    List<DepthListDto> getDepthList(Long hospitalId);
    String getDepth1NameOfHospital(@Param("hospitalId") Long hospitalId, @Param("depth1NavigationId") Long depth1NavigationId);
    Long findByDepth1(@Param("depth1NavigationId") Long depth1NavigationId, @Param("hospitalId") Long hospitalId);

    List<DepthListDto2> getDepth2List(Long depth1NavigationId);

    List<DepthListDto3> getDepth3List(@Param("depth2NavigationId") Long depth2NavigationId,
                                      @Param("roleId") Long roleId,
                                      @Param("hospitalId") Long hospitalId);
    List<Long> isChecked(Long roleId);

    void toShowCheck (InsertToShow insertToShow);

    void toHideCheck (Long roleId);

    List<RoleCategoryRes> getRoleCategories ();

    List<Integer> checkAuth(Long id);

    void authLog(AuthLogReq authLogReq);

    List<Long> findUserByRoleId(Long roleId);

    String findNavi3Id(Long roleId);
}
