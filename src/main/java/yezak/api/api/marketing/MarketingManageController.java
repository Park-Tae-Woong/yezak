package yezak.api.api.marketing;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yezak.api.api.marketing.admin.dto.*;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;
import yezak.api.api.marketing.admin.MarketingManageAdminService;
import yezak.api.util.FileService;

@RequestMapping(value = "/api/marketing/manage")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "Marketing-manage", description = "마케팅/상담 공통 api")
public class MarketingManageController {

    private final MarketingManageAdminService service;
    private final FileService fileService;

    @Operation(summary = "담당자 조회" , description = "담당자 조회")
    @GetMapping(value = "/user-list")
    public ResponseEntity<ApiResponse<?>> getUserList() {
        ResultResponse<?> resultResponse = service.getUserList();
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @Operation(summary = "상담상태 조회" , description = "상담상태 조회")
    @GetMapping(value = "/counseling-statuses-list")
    public ResponseEntity<ApiResponse<?>> getCounselingStatusesList() {
        ResultResponse<?> resultResponse = service.getCounselingStatusesList();
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @Operation(summary = "유입경로 조회" , description = "유입경로 조회")
    @GetMapping(value = "/access-root-list")
    public ResponseEntity<ApiResponse<?>> getAccessRootList() {
        ResultResponse<?> resultResponse = service.getAccessRootList();
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @Operation(summary = "신규등록 - 고객정보" , description = "신규등록 - 고객정보")
    @PostMapping(value = "/regist-info/patient")
    public ResponseEntity<ApiResponse<?>> registPatientInfo(@RequestBody MarketingManageRegistPatientInfoRequest request) {
        ResultResponse<?> resultResponse = service.registPatientInfo(request);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @Operation(summary = "신규등록 - 상담정보" , description = "신규등록 - 상담정보")
    @PostMapping(value = "/regist-info/counseling")
    public ResponseEntity<ApiResponse<?>> registCounselingInfo(@RequestBody MarketingManageRegistCounselingInfoRequest request) {
        ResultResponse<?> resultResponse = service.registCounselingInfo(request);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @Operation(summary = "신규등록 - 예약정보" , description = "신규등록 - 예약정보")
    @PostMapping(value = "/regist-info/reservation")
    public ResponseEntity<ApiResponse<?>> registReservationInfo(@RequestBody MarketingManageRegistReservationInfoRequest request) {
        ResultResponse<?> resultResponse = service.registReservationInfo(request);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @Operation(summary = "고객정보 수정" , description = "고객정보 수정")
    @PutMapping(value = "/modify-info/patient")
    public ResponseEntity<ApiResponse<?>> modifyPatientInfo(@RequestBody MarketingManageModifyPatientInfoRequest request) {
        ResultResponse<?> resultResponse = service.modifyPatientInfo(request);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @Operation(summary = "상세 페이지" , description = "상세 페이지",
            parameters = {
                    @Parameter(name = "id", description = "row id", example = "1")
            })
    @GetMapping(value = "/patient-detail")
    public ResponseEntity<ApiResponse<?>> getPatientDetail(@RequestParam Long id) {
        ResultResponse<?> resultResponse = service.getPatientDetail(id);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @Operation(summary = "예약취소" , description = "예약취소")
    @PutMapping(value = "/modify-reservation_status")
    public ResponseEntity<ApiResponse<?>> modifyReservationStatus(@RequestBody MarketingManageReservationCancelRequest request) {
        ResultResponse<?> resultResponse = service.modifyReservationStatus(request);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @Operation(summary = "양식 파일 다운로드" , description = "양식 파일 다운로드")
    @GetMapping(value = "/excel-download")
    public ResponseEntity<ApiResponse<?>> getMarketingExcelFile() {
        ResultResponse<?> resultResponse = fileService.getMarketingExcelFile();
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

//    @Operation(summary = "병원 상품 리스트 조회" , description = "병원 상품 리스트 조회",
//            parameters = {
//                    @Parameter(name = "hospitalId", description = "병원id", example = "1")
//            })
//    @GetMapping(value = "/hospital-product-list")
//    public ResponseEntity<List<MarketingManageHospitalProductInfo>> getHospitalProductList(@RequestParam int hospitalId) {
//        return ResponseEntity.ok().body(service.getHospitalProductList(hospitalId));
//    }

}
