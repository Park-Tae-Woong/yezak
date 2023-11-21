package yezak.api.api.user.login;

import yezak.api.api.user.dto.NewMemberUpdatePasswordReq;
import yezak.api.global.common.ResultResponse;

public interface NewMemberService {
    ResultResponse<?> newMemberUpdatePw(NewMemberUpdatePasswordReq memberUpdatePasswordReq);

}
