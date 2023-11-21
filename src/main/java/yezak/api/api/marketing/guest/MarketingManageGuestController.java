package yezak.api.api.marketing.guest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;

@RequestMapping(value = "/api/marketing/manage/guest")
@RequiredArgsConstructor
@RestController
@Slf4j
public class MarketingManageGuestController {

    private final MarketingManageGuestService service;

    @Operation(summary = "리스트 조회" , description = "리스트 조회")
    @GetMapping(value = "/list")
    public ResponseEntity<ApiResponse<?>> getList(){
        ResultResponse<?> resultResponse = service.getList();
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @Operation(summary = "DB 신규 등록" , description = "DB 신규 등록")
    @PostMapping(value = "/regist/db")
    public ResponseEntity<ApiResponse<?>> registDb(@Schema(description = "제목", example = "내일_23년 1-1차") @RequestParam String title, @Schema(description = "유입경로id", example = "1") @RequestParam Long accessRootId, @Schema(description = "집행금액", example = "100000") @RequestParam Long amount, @Schema(description = "집행일자", example = "2023-06-27") @RequestParam String executionDate, @Schema(description = "업로드파일(.xlsx)") @RequestParam MultipartFile file) {
        ResultResponse<?> resultResponse = service.registDb(title, accessRootId, amount, executionDate, file);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

}
