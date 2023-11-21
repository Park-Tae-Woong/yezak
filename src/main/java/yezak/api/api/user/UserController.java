package yezak.api.api.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yezak.api.api.user.dto.CheckPassword;
import yezak.api.api.user.dto.UpdatePasswordReq;
import yezak.api.api.user.dto.UserUpdateReq;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;

@RequestMapping(value = "/api/user")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "User-manage", description = "병원관리 > 계정/권한 관리")

public class UserController {
    private final UserService userService;

    //인적 정보 수정
    @PutMapping(value = "/update-my-page")
    @Operation(summary = "인적 정보 수정" , description = "인적 정보 수정")
    public ResponseEntity<ApiResponse<?>> updateMyPage(@RequestBody UserUpdateReq userUpdateReq){
        ResultResponse<?> resultResponse = userService.updateMyPage(userUpdateReq);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //비밀번호 변경
    @PutMapping(value = "/update-pw")
    @Operation(summary = "비밀번호 수정" , description = "비밀번호 수정")
    public ResponseEntity<ApiResponse<?>> updatePw(@RequestBody UpdatePasswordReq updatePasswordReq){
        ResultResponse<?> resultResponse = userService.updatePw(updatePasswordReq);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //계정 관리 세부 조회
    @GetMapping(value = "/my-page")
    @Operation(summary = "내정보 조회" , description = "내정보 조회")
    public ResponseEntity<ApiResponse<?>> getMyPage(){
        ResultResponse<?> resultResponse = userService.getMyPage();
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //비밀번호 확인
    @PostMapping(value = "/check-password")
    @Operation(summary = "비밀번호 확인" , description = "비밀번호 확인")
    public ResponseEntity<ApiResponse<?>> checkPw(@RequestBody CheckPassword checkPassword){
        ResultResponse<?> resultResponse = userService.checkPw(checkPassword);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }
}
