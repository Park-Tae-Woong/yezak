package yezak.api.api.user.join;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;
import yezak.api.api.user.dto.UserJoinReq;

import javax.mail.MessagingException;

@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "User-join", description = "회원가입")

public class UserJoinController {

	private final UserJoinService userJoinService;

	@PostMapping(value = "/join")
	@Operation(summary = "회원가입" , description = "회원가입")
	public ResponseEntity<ApiResponse<?>> insertUser(@RequestBody UserJoinReq req) throws MessagingException {
		ResultResponse<?> resultResponse = userJoinService.insertUser(req);
		return ResponseEntity.ok(ApiResponse.success(resultResponse));
	}
}
