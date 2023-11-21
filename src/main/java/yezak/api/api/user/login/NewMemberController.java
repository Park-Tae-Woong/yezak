package yezak.api.api.user.login;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yezak.api.api.user.dto.NewMemberUpdatePasswordReq;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;

@RequestMapping(value = "/api/new-member")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "New-Member-update-password", description = "신규회원 및 6개월 회원 비밀번호 변경")
public class NewMemberController {
    private final NewMemberService newMemberService;

    //신규회원 비밀번호 변경
    @PutMapping(value = "/update-password")
    @Operation(summary = "신규회원 비밀번호 수정" , description = "신규회원 비밀번호 수정")
    public ResponseEntity<ApiResponse<?>> newMemberUpdatePw(@RequestBody NewMemberUpdatePasswordReq newMemberUpdatePasswordReq){
        ResultResponse<?> resultResponse = newMemberService.newMemberUpdatePw(newMemberUpdatePasswordReq);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }
}
