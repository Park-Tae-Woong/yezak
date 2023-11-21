package yezak.api.api.user.login;

import org.springframework.http.ResponseEntity;
import yezak.api.api.user.dto.FindPasswordReq;
import yezak.api.api.user.dto.UserLoginReq;
import yezak.api.global.common.ResultResponse;

import javax.mail.MessagingException;

public interface UserLoginService {
    ResponseEntity<?> login(UserLoginReq req);
    ResultResponse<?> nameAndRoleName();
    ResponseEntity<String> findPassword(FindPasswordReq findPasswordReq) throws MessagingException;


}
