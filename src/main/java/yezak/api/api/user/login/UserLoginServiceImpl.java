package yezak.api.api.user.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yezak.api.api.user.join.UserJoinMapper;
import yezak.api.api.user.dto.FindPasswordReq;
import yezak.api.api.user.dto.UserLoginReq;
import yezak.api.domain.User;
import yezak.api.api.admin.audit_record.login_log.dto.LoginLogReq;
import yezak.api.global.common.ResultResponse;
import yezak.api.api.admin.audit_record.login_log.LoginLogMapper;
import yezak.api.security.jwt.JwtTokenProvider;
import yezak.api.security.jwt.TokenResponse;
import yezak.api.util.mail.MailRequest;
import yezak.api.util.mail.MailService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.net.*;
import java.time.LocalDateTime;
import java.util.*;

import static yezak.api.api.user.join.UserJoinServiceImpl.generateRandomPassword;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserLoginServiceImpl implements UserLoginService {

    private final UserLoginMapper userLoginMapper;
    private final LoginLogMapper loginLogMapper;
    private final UserJoinMapper userJoinMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final MailService mailService;

    @Autowired
    private final BCryptPasswordEncoder encoder;
    @Autowired
    private HttpServletRequest request;

    @Override
    public ResponseEntity<?> login(UserLoginReq userLoginReq) {
        User user = userLoginMapper.findByEmail(userLoginReq.getEmail());
        if(user == null){
            return new ResponseEntity<>("등록되지 않은 계정입니다. 이메일 주소를 확인해주세요.", HttpStatus.BAD_REQUEST);
        }
        String email = user.getEmail();
        String inputEmail = userLoginReq.getEmail();

        String password = user.getPassword();
        String temporaryPw = user.getTemporaryPassword();
        String inputPw = userLoginReq.getPassword();

        LocalDateTime temporaryTime = user.getTemporaryPasswordExpiredAt();
        LocalDateTime nowTime = LocalDateTime.now();

        if(user.getIsDeleted() != 0) {
            LoginLogReq failLoginLogReq = new LoginLogReq();
            failLoginLogReq.setHospitalId(user.getHospitalId());
            failLoginLogReq.setIp(getClientIp(request));
            failLoginLogReq.setMessage("삭제된 계정");
            failLoginLogReq.setResult("실패");
            failLoginLogReq.setActUserId(user.getId());
            failLoginLogReq.setAffectedUserId(user.getId());
            failLoginLogReq.setDistinction("로그인");
            loginLogMapper.insertLoginLog(failLoginLogReq);

            return new ResponseEntity<>("삭제된 계정입니다.", HttpStatus.BAD_REQUEST);
        }
        if (user.getIsStopped() != 0) {
            LoginLogReq failLoginLogReq = new LoginLogReq();
            failLoginLogReq.setHospitalId(user.getHospitalId());
            failLoginLogReq.setIp(getClientIp(request));
            failLoginLogReq.setMessage("사용정지 된 계정");
            failLoginLogReq.setResult("실패");
            failLoginLogReq.setActUserId(user.getId());
            failLoginLogReq.setAffectedUserId(user.getId());
            failLoginLogReq.setDistinction("로그인");
            loginLogMapper.insertLoginLog(failLoginLogReq);

            return new ResponseEntity<>("사용 정지된 계정입니다.", HttpStatus.BAD_REQUEST);
        }

        if ((Objects.equals(email, inputEmail) && encoder.matches(inputPw, password)) || (Objects.equals(email, inputEmail) && encoder.matches(inputPw, temporaryPw))){
//            if (user.getIsDeleted() != 0) {
//                LoginLogReq failLoginLogReq = new LoginLogReq();
//                failLoginLogReq.setHospitalId(user.getHospitalId());
//                failLoginLogReq.setIp(getClientIp());
//                failLoginLogReq.setMessage("삭제된 계정");
//                failLoginLogReq.setResult("실패");
//                failLoginLogReq.setActUserId(user.getId());
//                failLoginLogReq.setAffectedUserId(user.getId());
//                failLoginLogReq.setDistinction("로그인");
//                loginLogMapper.insertLoginLog(failLoginLogReq);
//
//                return new ResponseEntity<>("삭제된 계정입니다.", HttpStatus.BAD_REQUEST);
//            }
//            if (user.getIsStopped() != 0) {
//                LoginLogReq failLoginLogReq = new LoginLogReq();
//                failLoginLogReq.setHospitalId(user.getHospitalId());
//                failLoginLogReq.setIp(getClientIp());
//                failLoginLogReq.setMessage("사용정지 된 계정");
//                failLoginLogReq.setResult("실패");
//                failLoginLogReq.setActUserId(user.getId());
//                failLoginLogReq.setAffectedUserId(user.getId());
//                failLoginLogReq.setDistinction("로그인");
//                loginLogMapper.insertLoginLog(failLoginLogReq);
//
//                return new ResponseEntity<>("사용 정지된 계정입니다.", HttpStatus.BAD_REQUEST);
//            }
            if (userLoginMapper.getIsLocked(email) != 0) {
                LoginLogReq failLoginLogReq = new LoginLogReq();
                failLoginLogReq.setHospitalId(user.getHospitalId());
                failLoginLogReq.setIp(getClientIp(request));
                failLoginLogReq.setMessage("비활성화 된 계정");
                failLoginLogReq.setResult("실패");
                failLoginLogReq.setActUserId(user.getId());
                failLoginLogReq.setAffectedUserId(user.getId());
                failLoginLogReq.setDistinction("로그인");
                loginLogMapper.insertLoginLog(failLoginLogReq);

                return new ResponseEntity<>("접근 제한된 계정입니다.", HttpStatus.BAD_REQUEST);
            }
            if (temporaryTime != null && temporaryTime.isBefore(nowTime)){
                LoginLogReq failLoginLogReq = new LoginLogReq();
                failLoginLogReq.setHospitalId(user.getHospitalId());
                failLoginLogReq.setIp(getClientIp(request));
                failLoginLogReq.setMessage("임시비밀번호 만료");
                failLoginLogReq.setResult("실패");
                failLoginLogReq.setActUserId(user.getId());
                failLoginLogReq.setAffectedUserId(user.getId());
                failLoginLogReq.setDistinction("로그인");
                loginLogMapper.insertLoginLog(failLoginLogReq);

                return new ResponseEntity<>("만료된 임시비밀번호 입니다.", HttpStatus.BAD_REQUEST);
            }

            //토큰 생성값 변경
            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRoleId().toString()));
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getId(), null, authorities);
            TokenResponse tokenResponse = jwtTokenProvider.generateToken(authentication);

            //실패 횟수
            int count = userLoginMapper.getLoginCount(user.getEmail());

            Date lastPasswordChange = userLoginMapper.getLastPasswordChange(user.getEmail());
            Date getCreatedAt = userLoginMapper.getCreatedAt(user.getEmail());

            if (count >= 5 && userLoginMapper.getIsLocked(email) == 0){
                userLoginMapper.resetLoginFail(user.getEmail());
            }
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, -6);
            Date sixMonthsAgo = calendar.getTime();

            userLoginMapper.resetLoginFail(user.getEmail());
            userLoginMapper.login(userLoginReq);

            // 로그 정보 생성
            LoginLogReq loginLogReq = new LoginLogReq();
            loginLogReq.setHospitalId(user.getHospitalId());
            loginLogReq.setIp(getClientIp(request));
            loginLogReq.setMessage("-");
            loginLogReq.setResult("성공");
            loginLogReq.setActUserId(user.getId());
            loginLogReq.setAffectedUserId(user.getId());
            loginLogReq.setDistinction("로그인");
            // 로그 테이블에 로그 메시지 저장
            loginLogMapper.insertLoginLog(loginLogReq);

            //6개월
            if (lastPasswordChange.before(sixMonthsAgo)) {
                return ResponseEntity.status(210)
                        .body(tokenResponse);
            }
            //최초로그인
            else if (lastPasswordChange.equals(getCreatedAt)) {
                return ResponseEntity.status(210)
                        .body(tokenResponse);
            }
            else {
                return ResponseEntity.ok()
                        .body(tokenResponse);
            }
        } else {
            userLoginMapper.updateLoginFail(user.getEmail());
            int count = userLoginMapper.getLoginCount(user.getEmail());

            // 로그 정보 생성
            LoginLogReq failLoginLogReq = new LoginLogReq();
            failLoginLogReq.setHospitalId(user.getHospitalId());
            failLoginLogReq.setIp(getClientIp(request));
            failLoginLogReq.setMessage("비밀번호 불일치 " + count + "회");
            failLoginLogReq.setResult("실패");
            failLoginLogReq.setActUserId(user.getId());
            failLoginLogReq.setAffectedUserId(user.getId());
            failLoginLogReq.setDistinction("로그인");
            loginLogMapper.insertLoginLog(failLoginLogReq);

            if (count >= 5) {
                failLoginLogReq.setHospitalId(user.getHospitalId());
                failLoginLogReq.setIp(getClientIp(request));
                failLoginLogReq.setMessage("비활성화 된 계정");
                failLoginLogReq.setResult("실패");
                failLoginLogReq.setActUserId(user.getId());
                failLoginLogReq.setAffectedUserId(user.getId());
                failLoginLogReq.setDistinction("로그인");
                loginLogMapper.insertLoginLog(failLoginLogReq);
                return new ResponseEntity<>("접근 제한된 계정입니다.", HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>("비밀번호를 잘못 입력하였습니다. 5회 오류시, 해당 계정의 시스템 접근이 제한됩니다." + "(" + count + "/5)", HttpStatus.valueOf(424));
        }
    }

    @Override
    public ResultResponse<?> nameAndRoleName() {
        User user = userLoginMapper.findById((Long)request.getAttribute("userId"));
        Map<String, Object> map = new HashMap<>();
        map.put("nameAndRoleName", userLoginMapper.nameAndRoleName(user.getId()));
        map.put("navi3List", userLoginMapper.myNaviList(user.getRoleId()));
        map.put("gradeId", userJoinMapper.findGradeByUserId(user.getId()));
        return ResultResponse.builder()
                .data(map)
                .result(true)
                .resultCode("200")
                .build();
    }

    @Transactional
    @Override
    public ResponseEntity<String> findPassword(FindPasswordReq findPasswordReq) throws MessagingException {
        User user = userLoginMapper.findByEmail(findPasswordReq.getEmail());
        if(user == null){
            return new ResponseEntity<>("등록되지 않은 계정입니다. 이메일 주소를 확인해주세요.", HttpStatus.BAD_REQUEST);
        }
        if (user.getIsStopped() != 0) {
            return new ResponseEntity<>("사용 정지된 계정입니다.", HttpStatus.BAD_REQUEST);
        }

        String pass = generateRandomPassword();
        String encodePass = encoder.encode(pass);
        findPasswordReq.setPassword(encodePass);

        MailRequest mailRequest = new MailRequest();
        mailRequest.setToEmail(findPasswordReq.getEmail());
        mailRequest.setSubject("임시 비밀번호");
        mailRequest.setMessage("<html><body>안녕하세요. DARWIN 입니다.<br/>입력하신 이메일 주소의 비밀번호는 하단에 있는 비밀번호 재설정 링크를 클릭해 재설정하실 수 있습니다." +
                "<br/><br/>하단의 임시비밀번호를 통해 최초 로그인을 해주세요.<br/><br/>DARWIN 바로가기     https://docs-yzk.netlify.app/auth<br/><br/>임시비밀번호 : " + pass + "<br/><br/>" +
                "* 위 임시비밀번호는 1시간이 지나면 만료됩니다.<br/>메일이 도착한지 1시간이 지났다면 병원 관리자에게 재문의하십시오.</body></html>");
        mailService.sendMail(mailRequest);

        userLoginMapper.resetPassword(findPasswordReq.getEmail());
        userLoginMapper.findPassword(findPasswordReq);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public String getClientIp(HttpServletRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("HttpServletRequest cannot be null");
        }

        String ipAddress = request.getHeader("X-Forwarded-For");

        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

//    public String getServerIp() {
//        try {
//            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
//            while(interfaces.hasMoreElements()) {
//                NetworkInterface ni = interfaces.nextElement();
//
//                //MAC 주소 조회
//                byte[] hardwareAddress = ni.getHardwareAddress();
//
//                //VirtualBox의 MAC 주소 형태의 차이로 구별
//                boolean virtual = false;
//                if(hardwareAddress != null) {
//                    //hardwareAddress와 localIp 값을 각각 출력해보고
//                    //Virtual Box IP가 아닐 때의 MAC 주소를 아래 조건문을 통해 필터링
//                    if(hardwareAddress[0] != -44)
//                        virtual = true;
//                }
//                if(virtual) continue;
//
//                for(InterfaceAddress addr : ni.getInterfaceAddresses()) {
//                    InetAddress address = addr.getAddress();
//                    if(address.isSiteLocalAddress()) {
//                        //주소 문자열에서 /가 나오는 것을 치환
//                        return addr.getAddress().toString().replace("/", "");
//                    }
//                }
//            }
//        } catch (SocketException e) {
//            e.printStackTrace();
//        }
//
//        //Virtual Box가 없는 경우 서버 IP를 조회하는 기존 코드
//        try {
//            return InetAddress.getLocalHost().getHostAddress();
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

}
