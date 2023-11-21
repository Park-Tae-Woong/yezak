package yezak.api.api.user.login;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yezak.api.api.user.dto.FindPasswordReq;
import yezak.api.api.user.dto.UserLoginReq;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;

import javax.mail.MessagingException;


@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "Login", description = "로그인")

public class UserLoginController {

	private final UserLoginService userLoginService;

	@PostMapping(value = "/login")
	@Operation(summary = "로그인" , description = "로그인")
	public ResponseEntity<?> login(@RequestBody UserLoginReq req) {
		return userLoginService.login(req);
	}

	@GetMapping(value = "/get-mine")
	@Operation(summary = "이름, 직군 이름, 권한 리스트" , description = "이름, 직군이름, 권한리스트")
	public ResponseEntity<ApiResponse<?>> nameAndRoleName(){
		ResultResponse<?> resultResponse = userLoginService.nameAndRoleName();
		return ResponseEntity.ok(ApiResponse.success(resultResponse));
	}

	@PostMapping(value = "/find-password")
	@Operation(summary = "비밀번호 찾기" , description = "비밀번호 찾기")
	public ResponseEntity<String> findPassword(@RequestBody FindPasswordReq findPasswordReq) throws MessagingException {
		return userLoginService.findPassword(findPasswordReq);
	}
}