package yezak.api.api.marketing.dashboard;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;


@RequestMapping(value = "/api/marketing/dashboard")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "Marketing-Dashboard", description = "마케팅/상담 > 대시보드 api")
public class MarketingDashboardController {

    private final MarketingDashboardService service;

    @Operation(summary = "연도 조회" , description = "연도 조회")
    @GetMapping(value = "/get-years-list")
    public ResponseEntity<ApiResponse<?>> getYearsList() {
        ResultResponse<?> resultResponse = service.getYearsList();
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @Operation(summary = "월 매출액 조회" , description = "월 매출액 조회",
            parameters = {
                    @Parameter(name = "year", description = "연도", example = "2023"),
                    @Parameter(name = "month", description = "월", example = "07")
            })
    @GetMapping(value = "/get-goals")
    public ResponseEntity<ApiResponse<?>> getHospitalGoals(@RequestParam String year, @RequestParam String month) {
        ResultResponse<?> resultResponse = service.getHospitalGoals(year, month);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @Operation(summary = "DB 유입경로 조회" , description = "DB 유입경로 조회",
                parameters = {
                        @Parameter(name = "year", description = "연도", example = "2023"),
                        @Parameter(name = "month", description = "월", example = "07")
                })
    @GetMapping(value = "/get-access-root")
    public ResponseEntity<ApiResponse<?>> getAccessRoot(@RequestParam String year, @RequestParam String month) {
        ResultResponse<?> resultResponse = service.getAccessRoot(year, month);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @Operation(summary = "성별/연령 조회" , description = "성별/연령 조회",
            parameters = {
                    @Parameter(name = "year", description = "연도", example = "2023"),
                    @Parameter(name = "month", description = "월", example = "07")
            })
    @GetMapping(value = "/get-gender-age")
    public ResponseEntity<ApiResponse<?>> getGenderAge(@RequestParam String year, @RequestParam String month) {
        ResultResponse<?> resultResponse = service.getGenderAge(year, month);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @Operation(summary = "환자 지역 분포 조회" , description = "환자 지역 분포 조회",
            parameters = {
                    @Parameter(name = "year", description = "연도", example = "2023"),
                    @Parameter(name = "month", description = "월", example = "07")
            })
    @GetMapping(value = "/get-region")
    public ResponseEntity<ApiResponse<?>> getRegion(@RequestParam String year, @RequestParam String month) {
        ResultResponse<?> resultResponse = service.getRegion(year, month);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @Operation(summary = "광고비 대비 수익률 조회" , description = "광고비 대비 수익률 조회",
            parameters = {
                    @Parameter(name = "year", description = "연도", example = "2023"),
                    @Parameter(name = "month", description = "월", example = "07")
            })
    @GetMapping(value = "/get-ad-expense-profit")
    public ResponseEntity<ApiResponse<?>> getAdExpenseProfit(@RequestParam String year, @RequestParam String month) {
        ResultResponse<?> resultResponse = service.getAdExpenseProfit(year, month);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }


}
