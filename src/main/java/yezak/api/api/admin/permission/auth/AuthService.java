package yezak.api.api.admin.permission.auth;

import yezak.api.api.admin.permission.auth.dto.ExposedOrNot;
import yezak.api.global.common.ResultResponse;

public interface AuthService {

    ResultResponse<?> allDepthList(Long roleId);

    ResultResponse<?> ShowOrHideCheck (ExposedOrNot exposedOrNot);

    ResultResponse<?> getRoleCategories ();


}
