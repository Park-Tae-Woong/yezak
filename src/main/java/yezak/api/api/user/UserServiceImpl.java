package yezak.api.api.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yezak.api.api.user.dto.*;
import yezak.api.domain.User;
import yezak.api.global.common.ResultResponse;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

import static yezak.api.config.MyIdConfig.myUserId;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder encoder;
    private final HttpServletRequest request;

    @Value("${jwt.secret}")
    private String key;

    @Override
    public ResultResponse<?> updateMyPage(UserUpdateReq userUpdateReq){
        userUpdateReq.setId(myUserId());
        userMapper.updateMyPage(userUpdateReq);
        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .resultMessage("수정되었습니다.")
                .build();
    }

    @Override
    public ResultResponse<?> updatePw(UpdatePasswordReq updatePasswordReq) {
        User user = userMapper.findById((Long)request.getAttribute("userId"));
        String password = updatePasswordReq.getPassword();
        String pw = user.getPassword();

        CheckLastPassword checkLastPassword = userMapper.checkLastPassword(user.getEmail());

        // 정규식 패턴(8자리 이상, 숫자 영문자 특수문자 중 2가지 이상 조합)
        if (encoder.matches(password, pw)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("400")
                    .resultMessage("같은 비밀번호입니다.")
                    .build();
        }

        if (isValid(password)) {

            String last = checkLastPassword.getLastPassword();
            String lastLast = checkLastPassword.getLastLastPassword();

            if ((last != null && encoder.matches(password, last)) || encoder.matches(password, lastLast)) {
                return ResultResponse.builder()
                        .result(false)
                        .resultCode("400")
                        .resultMessage("직전 비밀번호 사용 및 교대사용이 불가능합니다.")
                        .build();
            }
            String encodedPassword = encoder.encode(password);
            updatePasswordReq.setPassword(encodedPassword);
            updatePasswordReq.setUserId(user.getId());

            UpdateLastPasswordReq updateLastPasswordReq = UpdateLastPasswordReq.builder()
                    .email(user.getEmail())
                    .lastPassword(pw)
                    .build();
            UpdateLastLastPasswordReq updateLastLastPasswordReq = UpdateLastLastPasswordReq.builder()
                    .email(user.getEmail())
                    .lastLastPassword(last)
                    .build();

            userMapper.updateLastLastPassword(updateLastLastPasswordReq);
            userMapper.updateLastPassword(updateLastPasswordReq);
            userMapper.updatePw(updatePasswordReq);
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

    @Override
    public ResultResponse<?> getMyPage() {
        return ResultResponse.builder()
                .data(userMapper.getMyPage(myUserId()))
                .result(true)
                .resultCode("200")
                .build();
    }

    @Override
    public ResultResponse<?> checkPw(CheckPassword checkPassword) {
        User user = userMapper.findById(myUserId());
        String password = user.getPassword();
        String tempPassword = user.getTemporaryPassword();
        String inputPw = checkPassword.getPassword();

        LocalDateTime temporaryTime = user.getTemporaryPasswordExpiredAt();
        LocalDateTime nowTime = LocalDateTime.now();
        if (encoder.matches(inputPw, password) || encoder.matches(inputPw, tempPassword)) {
            if (temporaryTime != null && temporaryTime.isBefore(nowTime)){
                return ResultResponse.builder()
                        .result(false)
                        .resultCode("400")
                        .resultMessage("만료된 임시비밀번호 입니다.")
                        .build();
            }
            return ResultResponse.builder()
                    .result(true)
                    .resultCode("200")
                    .resultMessage("비밀번호 일치")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("400")
                    .resultMessage("비밀번호 불일치")
                    .build();
        }
    }


    public static boolean isValid(String password) {
        int requiredCharacterClasses = 0;

        // 비밀번호 길이 확인
        if (password.length() < 8) {
            return false;
        }

        // 숫자 포함 여부 확인
        if (password.matches(".*\\d.*")) {
            requiredCharacterClasses++;
        }

        // 영문자 포함 여부 확인
        if (password.matches(".*[a-zA-Z].*")) {
            requiredCharacterClasses++;
        }

        // 특수문자 포함 여부 확인
        if (password.matches(".*[@#$%^&+=!_\\-].*")) {
            requiredCharacterClasses++;
        }

        // 각각의 문자 클래스가 최소 1개 이상 포함되었는지 확인
        if (requiredCharacterClasses >= 2) {
            return true;
        } else {
            return false;
        }
    }

}
