package yezak.api.api.marketing.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yezak.api.api.marketing.admin.dto.MarketingManageAdminSetAutoUserPatientRequest;
import yezak.api.api.marketing.admin.dto.MarketingManageAdminSetManualUserPatientRequest;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;

@RequestMapping(value = "/api/marketing/manage/admin")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "Marketing-manage-admin", description = "마케팅/상담 > 전체DB관리 api")
public class MarketingManageAdminController {

    private final MarketingManageAdminService service;

    @Operation(summary = "대시보드 - 총DB수 조회" , description = "대시보드 - 총DB수 조회",
            parameters = {
                    @Parameter(name = "startDate", description = "시작일", example = "2023-05-01"),
                    @Parameter(name = "endDate", description = "종료일", example = "2023-05-31")
            })
    @GetMapping(value = "/get-total-db-count")
    public ResponseEntity<ApiResponse<?>> getTotalDbCount(@RequestParam String startDate, @RequestParam String endDate) {
        ResultResponse<?> resultResponse = service.getTotalDbCount(startDate, endDate);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @Operation(summary = "대시보드 - 내원예약수" , description = "대시보드 - 내원예약수",
            parameters = {
                    @Parameter(name = "startDate", description = "시작일", example = "2023-05-01"),
                    @Parameter(name = "endDate", description = "종료일", example = "2023-05-31")
            })
    @GetMapping(value = "/get-reservation-count")
    public ResponseEntity<ApiResponse<?>> getReservationCount(@RequestParam String startDate, @RequestParam String endDate) {
        ResultResponse<?> resultResponse = service.getReservationCount(startDate, endDate);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @Operation(summary = "대시보드 - 예약금" , description = "대시보드 - 예약금",
            parameters = {
                    @Parameter(name = "startDate", description = "시작일", example = "2023-05-01"),
                    @Parameter(name = "endDate", description = "종료일", example = "2023-05-31")
            })
    @GetMapping(value = "/get-reservationfee-amount")
    public ResponseEntity<ApiResponse<?>> getReservationFeeAmount(@RequestParam String startDate, @RequestParam String endDate) {
        ResultResponse<?> resultResponse = service.getReservationFeeAmount(startDate, endDate);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @Operation(summary = "대시보드 - 성별/연령" , description = "대시보드 - 성별/연령",
            parameters = {
                    @Parameter(name = "startDate", description = "시작일", example = "2023-05-01"),
                    @Parameter(name = "endDate", description = "종료일", example = "2023-05-31")
            })
    @GetMapping(value = "/get-gender-age-count")
    public ResponseEntity<ApiResponse<?>> getGenderAgeCount(@RequestParam String startDate, @RequestParam String endDate) {
        ResultResponse<?> resultResponse = service.getGenderAgeCount(startDate, endDate);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @Operation(summary = "대시보드 - DB유입경로" , description = "대시보드 - DB유입경로",
            parameters = {
                    @Parameter(name = "startDate", description = "시작일", example = "2023-05-01"),
                    @Parameter(name = "endDate", description = "종료일", example = "2023-05-31")
            })
    @GetMapping(value = "/get-access-root-count")
    public ResponseEntity<ApiResponse<?>> getAccessRootCount(@RequestParam String startDate, @RequestParam String endDate) {
        ResultResponse<?> resultResponse = service.getAccessRootCount(startDate, endDate);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @Operation(summary = "DB현황" , description = "DB현황",
            parameters = {
                    @Parameter(name = "startDate", description = "시작일", example = "2023-05-01"),
                    @Parameter(name = "endDate", description = "종료일", example = "2023-05-31")
            })
    @GetMapping(value = "/get-db-current-state-count")
    public ResponseEntity<ApiResponse<?>> getDbCurrentStateCount(@RequestParam String startDate, @RequestParam String endDate) {
        ResultResponse<?> resultResponse = service.getDbCurrentStateCount(startDate, endDate);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @Operation(summary = "DB 담당 현황" , description = "DB 담당 현황",
            parameters = {
                    @Parameter(name = "startDate", description = "시작일", example = "2023-05-01"),
                    @Parameter(name = "endDate", description = "종료일", example = "2023-05-31")
            })
    @GetMapping(value = "/get-db-charge-state-count")
    public ResponseEntity<ApiResponse<?>> getDbChargeStateCount(@RequestParam String startDate, @RequestParam String endDate) {
        ResultResponse<?> resultResponse = service.getDbChargeStateCount(startDate, endDate);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @Operation(summary = "선택삭제" , description = "선택삭제",
            parameters = {
                    @Parameter(name = "idArr", description = "선택한 row id", example = "1,2,3")
            })
    @DeleteMapping(value = "/delete-data")
    public ResponseEntity<ApiResponse<?>> deleteData(@RequestParam String idArr) {
        ResultResponse<?> resultResponse = service.deleteData(idArr);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @Operation(summary = "담당자 수동 지정", description = "담당자 수동 지정")
    @PostMapping(value = "/set-manual-user-patient")
    public ResponseEntity<ApiResponse<?>> setManualUserPatient(@RequestBody MarketingManageAdminSetManualUserPatientRequest request) {
        ResultResponse<?> resultResponse = service.setManualUserPatient(request);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @Operation(summary = "DB 자동 분배", description = "DB 자동 분배")
    @PostMapping(value = "/set-auto-user-patient")
    public ResponseEntity<ApiResponse<?>> setAutoUserPatient(@RequestBody MarketingManageAdminSetAutoUserPatientRequest request) {
        ResultResponse<?> resultResponse = service.setAutoUserPatient(request);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));

    }

    @Operation(summary = "DB검색(이름or전화번호)" , description = "DB검색(이름or전화번호)",
            parameters = {
                    @Parameter(name = "param", description = "이름 or 전화번호", example = "홍길동 or 010-1234-5678")
            })
    @GetMapping(value = "/search-patient")
    public ResponseEntity<ApiResponse<?>> searchPatient(@RequestParam String param) {
        ResultResponse<?> resultResponse = service.searchPatient(param);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @Operation(summary = "엑셀 업로드" , description = "엑셀 업로드")
    @PostMapping(value = "/excel-upload")
    public ResponseEntity<ApiResponse<?>> excelUpload(@RequestParam @Schema(description = "업로드파일(.xlsx)") MultipartFile file) {
        ResultResponse<?> resultResponse = service.excelUpload(file);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

}
