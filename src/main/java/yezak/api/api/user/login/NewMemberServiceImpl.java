package yezak.api.api.user.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yezak.api.api.user.UserMapper;
import yezak.api.domain.User;
import yezak.api.api.user.dto.CheckLastPassword;
import yezak.api.api.user.dto.NewMemberUpdatePasswordReq;
import yezak.api.api.user.dto.UpdateLastLastPasswordReq;
import yezak.api.api.user.dto.UpdateLastPasswordReq;
import yezak.api.global.common.ResultResponse;

import static yezak.api.api.user.UserServiceImpl.isValid;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class NewMemberServiceImpl implements NewMemberService{
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder encoder;

    @Override
    public ResultResponse<?> newMemberUpdatePw(NewMemberUpdatePasswordReq memberUpdatePasswordReq) {
        User user = userMapper.findByEmail(memberUpdatePasswordReq.getEmail());
        String password = memberUpdatePasswordReq.getPassword();

        String pw = null;

        if (user.getPassword() == null) {
            pw = user.getTemporaryPassword();
        } else {
            pw = user.getPassword();
        }

        // 정규식 패턴(8자리 이상, 숫자 영문자 특수문자 중 2가지 이상 조합)
        if (encoder.matches(password, pw)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("400")
                    .resultMessage("기존 비밀번호를 새비밀번호로 재사용 할 수 없습니다.")
                    .build();
        }

        if (isValid(password)) {
            CheckLastPassword checkLastPassword = userMapper.checkLastPassword(user.getEmail());
            String last = checkLastPassword.getLastPassword();
            String lastLast = checkLastPassword.getLastLastPassword();

            if (encoder.matches(password, last) || encoder.matches(password, lastLast)) {
                return ResultResponse.builder()
                        .result(false)
                        .resultCode("400")
                        .resultMessage("직전 비밀번호 사용 및 교대사용이 불가능합니다.")
                        .build();
            }
            String encodedPassword = encoder.encode(password);
            memberUpdatePasswordReq.setPassword(encodedPassword);
            memberUpdatePasswordReq.setEmail(user.getEmail());
            userMapper.deleteTempPw(user.getEmail());

            UpdateLastLastPasswordReq updateLastLastPasswordReq = null;
            if (user.getLastPassword() != null) {
                updateLastLastPasswordReq = UpdateLastLastPasswordReq.builder()
                        .email(user.getEmail())
                        .lastLastPassword(last)
                        .build();
            }

            UpdateLastPasswordReq updateLastPasswordReq = UpdateLastPasswordReq.builder()
                    .email(user.getEmail())
                    .lastPassword(pw)
                    .build();

            userMapper.updateLastLastPassword(updateLastLastPasswordReq);
            userMapper.updateLastPassword(updateLastPasswordReq);
            userMapper.newMemberUpdatePw(memberUpdatePasswordReq);

            return ResultResponse.builder()
                    .result(true)
                    .resultCode("200")
                    .resultMessage("수정되었습니다.")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("400")
                    .resultMessage("올바르지 않은 비밀번호입니다.")
                    .build();
        }
    }
}
