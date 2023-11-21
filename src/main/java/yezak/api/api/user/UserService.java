package yezak.api.api.user;

import yezak.api.api.user.dto.CheckPassword;
import yezak.api.api.user.dto.UpdatePasswordReq;
import yezak.api.api.user.dto.UserUpdateReq;
import yezak.api.global.common.ResultResponse;

public interface UserService {

    ResultResponse<?> updateMyPage(UserUpdateReq userUpdateReq);

    ResultResponse<?> updatePw(UpdatePasswordReq updatePasswordReq);

    ResultResponse<?> getMyPage();

    ResultResponse<?> checkPw(CheckPassword checkPassword);



}
