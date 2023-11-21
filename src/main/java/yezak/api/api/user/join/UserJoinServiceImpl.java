package yezak.api.api.user.join;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yezak.api.domain.User;
import yezak.api.api.admin.audit_record.login_log.dto.LoginLogReq;
import yezak.api.api.user.dto.UserJoinReq;
import yezak.api.global.common.ResultResponse;
import yezak.api.util.mail.MailRequest;
import yezak.api.util.mail.MailService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;
import java.util.Optional;

import static yezak.api.config.MyIdConfig.myHospitalId;
import static yezak.api.config.MyIdConfig.myUserId;


@RequiredArgsConstructor
@Service
@Slf4j
public class UserJoinServiceImpl implements UserJoinService {

	private final UserJoinMapper userJoinMapper;

	private final MailService mailService;

	private final BCryptPasswordEncoder encoder;
	private final HttpServletRequest request;

	@Override
	@Transactional
	public ResultResponse<?> insertUser(UserJoinReq req) throws MessagingException {
		String email = req.getEmail();
		Optional<User> existUser = userJoinMapper.findByEmail(email);
		String emailPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
		String pass = generateRandomPassword();
		String sendPass = encoder.encode(pass);
		req.setTemporaryPassword(sendPass);

		if (userJoinMapper.findGradeByUserId(myUserId()) == 3L) {
			return ResultResponse.builder()
					.result(false)
					.resultCode("400")
					.resultMessage("관리자만 초대 가능합니다.")
					.build();
		}
		if (userJoinMapper.findDeleteByEmail(email) != null && userJoinMapper.findDeleteByEmail(email) == 1) {
			return ResultResponse.builder()
					.result(false)
					.resultCode("400")
					.resultMessage("해당 계정은 삭제처리된 계정입니다. 다른 이메일 계정을 입력해주세요.")
					.build();
		}
		// 이메일 형식이 올바른지 확인
		if (!email.matches(emailPattern)) {
			return ResultResponse.builder()
					.result(false)
					.resultCode("400")
					.resultMessage("잘못된 이메일 형식입니다.")
					.build();
		}

		if (existUser.isPresent()){
			return ResultResponse.builder()
					.result(false)
					.resultCode("400")
					.resultMessage("중복된 사용자 이메일 계정이 있습니다.\n" +
							"해당 계정상태를 활성화 시키거나, \n" +
							"다른 이메일 계정을 입력해주세요.\n")
					.build();
		}

		MailRequest mailRequest = new MailRequest();
		mailRequest.setToEmail(email);
		mailRequest.setSubject("초대 메일");
		mailRequest.setMessage("<html><body>안녕하세요. DARWIN 입니다.<br/>입력하신 이메일 주소의 비밀번호는 하단에 있는 비밀번호 재설정 링크를 클릭해 재설정하실 수 있습니다." +
				"<br/><br/>하단의 임시비밀번호를 통해 최초 로그인을 해주세요.<br/><br/>DARWIN 바로가기     https://docs-yzk.netlify.app/auth<br/><br/>임시비밀번호 : " + pass + "<br/><br/>" +
				"* 위 임시비밀번호는 1시간이 지나면 만료됩니다.<br/>메일이 도착한지 1시간이 지났다면 병원 관리자에게 재문의하십시오.</body></html>");
		req.setHospitalId(myHospitalId());
		userJoinMapper.insertUser(req);
		userJoinMapper.firstEmploymentInfo(req.getId());
		userJoinMapper.firstPersonalInfo(req.getId());

		LoginLogReq loginLogReq = LoginLogReq.builder()
				.hospitalId(myHospitalId())
				.ip(getClientIp())
				.message("발급")
				.actUserId(myUserId())
				.affectedUserId(req.getId())
				.result("성공")
				.distinction("계정")
				.build();
		userJoinMapper.userJoinLog(loginLogReq);

		mailService.sendMail(mailRequest);

		return ResultResponse.builder()
				.result(true)
				.resultCode("200")
				.resultMessage("초대 메일 전송")
				.build();

	}

	public static String generateRandomPassword() {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 10; i++) {
			int index = random.nextInt(chars.length());
			sb.append(chars.charAt(index));
		}
		return sb.toString();
	}
	public String getClientIp() {
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


}
