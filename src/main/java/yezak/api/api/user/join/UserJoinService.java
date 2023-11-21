package yezak.api.api.user.join;

import yezak.api.api.user.dto.UserJoinReq;
import yezak.api.global.common.ResultResponse;

import javax.mail.MessagingException;

public interface UserJoinService {
	ResultResponse<?> insertUser(UserJoinReq userJoinReq) throws MessagingException;


}
