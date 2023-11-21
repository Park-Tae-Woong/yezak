package yezak.api.api.admin.audit_record.login_log;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping(value = "/api/login-log")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "Audit-record", description = "병원관리 > 감사기록")
public class LoginLogController {
    private final LoginLogService loginLogService;

    //로그 리스트
    @GetMapping(value = "/get-log")
    @Operation(summary = "로그인 로그 목록" , description = "로그인 로그 리스트")
    public ResponseEntity<ApiResponse<?>> getLoginLogList(@RequestParam(required = false)String start,
                                                          @RequestParam(required = false)String end,
                                                          @RequestParam Integer pageNum,
                                                          @RequestParam Integer pageSize){
        ResultResponse<?> resultResponse = loginLogService.getLoginLogList(start, end, pageNum, pageSize);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @GetMapping(value = "/excel-log")
    @Operation(summary = "엑셀 다운로드" , description = "엑셀 다운로드")
    public void loginLogExcel(HttpServletResponse response, @RequestParam(required = false)String start, @RequestParam(required = false)String end) throws IOException {
        loginLogService.loginLogExcel(response, start, end);
    }

}
