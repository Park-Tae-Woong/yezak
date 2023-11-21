package yezak.api.api.admin.permission.account;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yezak.api.api.admin.permission.account.dto.EmplInfoReq;
import yezak.api.api.admin.permission.account.dto.SalaryUpdateReq;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;

@RequestMapping(value = "/api/account")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "Account-manage", description = "병원관리 > 계정/권한관리 > 계정관리")
public class AccountController {

    private final AccountService accountService;

    //계정 관리 리스트
    @GetMapping(value = "/get-user-managements")
    @Operation(summary = "계정관리 리스트" , description = "계정관리 리스트")
    public ResponseEntity<ApiResponse<?>> getUsers(){
        ResultResponse<?> resultResponse = accountService.getUsers();
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //계정 관리 세부 조회
    @GetMapping(value = "/get-user-manage-detail")
    @Operation(summary = "계정관리 세부 조회" , description = "계정관리 세부 조회", parameters = {
            @Parameter(name = "userId", description = "유저id")})
    public ResponseEntity<ApiResponse<?>> getUserDetail(@RequestParam Long userId){
        ResultResponse<?> resultResponse = accountService.getUserDetail(userId);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //급여 정보 수정
    @PutMapping(value = "/update-salary")
    @Operation(summary = "급여 정보 수정" , description = "급여 정보 수정")
    public ResponseEntity<ApiResponse<?>> updateSalary(@RequestBody SalaryUpdateReq salaryUpdateReq){
        ResultResponse<?> resultResponse = accountService.updateSalary(salaryUpdateReq);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //근무 정보 수정
    @PutMapping(value = "/update-empl-info")
    @Operation(summary = "근무 정보 수정" , description = "근무 정보 수정")
    public ResponseEntity<ApiResponse<?>> updateEmplInfo(@RequestBody EmplInfoReq emplInfoReq){
        ResultResponse<?> resultResponse = accountService.updateEmplInfo(emplInfoReq);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //계정 상태 활성화
    @PutMapping(value = "/status-able")
    @Operation(summary = "계정 상태 활성화" , description = "계정 상태 활성화", parameters = {
            @Parameter(name = "id", description = "유저id", example = "1")})
    public ResponseEntity<ApiResponse<?>> updateStatusAble(@RequestParam Long id){
        ResultResponse<?> resultResponse = accountService.updateStatusAble(id);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //계정 상태 삭제
    @PutMapping(value = "/status-delete")
    @Operation(summary = "계정 상태 삭제" , description = "계정 상태 삭제", parameters = {
            @Parameter(name = "id", description = "유저id", example = "1")})
    public ResponseEntity<ApiResponse<?>> updateStatusDelete(@RequestParam Long id){
        ResultResponse<?> resultResponse = accountService.updateStatusDelete(id);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //계정 상태 비활성화
    @PutMapping(value = "/status-stop")
    @Operation(summary = "계정 상태 비활성화" , description = "계정 상태 비활성화", parameters = {
            @Parameter(name = "id", description = "유저id", example = "1")})
    public ResponseEntity<ApiResponse<?>> updateStatusStop(@RequestParam Long id){
        ResultResponse<?> resultResponse = accountService.updateStatusStop(id);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }
}
