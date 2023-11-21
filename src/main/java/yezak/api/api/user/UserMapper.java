package yezak.api.api.user;

import org.apache.ibatis.annotations.Mapper;
import yezak.api.api.user.dto.*;
import yezak.api.domain.User;

@Mapper
public interface UserMapper {
    void updateMyPage(UserUpdateReq userUpdateReq);

    void updatePw(UpdatePasswordReq updatePasswordReq);

    User findByEmail(String email);
    User findById(Long userId);

    GetMyPageRes getMyPage(Long id);

    void newMemberUpdatePw(NewMemberUpdatePasswordReq memberUpdatePasswordReq);
    void deleteTempPw(String email);

    void updateLastPassword (UpdateLastPasswordReq updateLastPasswordReq);
    void updateLastLastPassword (UpdateLastLastPasswordReq updateLastLastPasswordReq);
    CheckLastPassword checkLastPassword (String email);

}
