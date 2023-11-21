package yezak.api.api.schedule.counseling;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yezak.api.api.schedule.counseling.dto.*;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;

@RequestMapping(value = "/api/counseling")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "Counseling", description = "스케줄 > 대면상담실")
public class CounselingController {
    private final CounselingService counselingService;

    /**
     * 대면상담실 리스트(셀렉트박스용) 조회
     */
    @GetMapping(value = "/counseling-room-list")
    @Operation(summary = "대면상담실 리스트 셀렉트박스")
    public ResponseEntity<ApiResponse> selectCounselingRoomList(@Parameter(hidden = true) Long hospitalId){
        ResultResponse result = counselingService.selectCounselingRoomList(hospitalId);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    /**
     * 환자 조회
     */
    @GetMapping(value = "/patients-list")
    @Operation(summary = "환자 조회",
            parameters = {
                    @Parameter(name = "searchValue", in = ParameterIn.QUERY, required = false, description = "이름/차트번호/생년월일(6자리)/전화번호", example = "홍길동")
            })
    public ResponseEntity<ApiResponse> selectPatients(@Parameter(hidden = true) SearchPatientsRequest searchPatientsRequest) {
        ResultResponse result = counselingService.selectPatients(searchPatientsRequest);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    /**
     * 대기 리스트 조회
     */
    @GetMapping(value = "/waiting-list/{roomId}")
    @Operation(summary = "대기 리스트 조회")
    public ResponseEntity<ApiResponse> selectWaitingList(@PathVariable(name = "roomId") Long roomId, WaitingListRequest waitingListRequest) {
        ResultResponse result = counselingService.selectWaitingList(roomId, waitingListRequest);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    /**
     * 대기리스트 상태 업데이트
     */
    @PutMapping(value = "/update-status")
    @Operation(summary = "대기리스트 상태 업데이트")
    public ResponseEntity<ApiResponse> updateWaitingStatus(@RequestBody UpdateWaitingStatusRequest updateWaitingStatusRequest){
        ResultResponse result = counselingService.updateWaitingStatus(updateWaitingStatusRequest);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    /**
     * 환자 상세 조회
     */
    @GetMapping(value = "/patient-info/{id}")
    @Operation(summary = "환자 정보 조회")
    public ResponseEntity<ApiResponse> selectPatientInfo(@PathVariable(name = "id") Long id){
        ResultResponse result = counselingService.selectPatientInfo(id);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    /**
     * 바이탈 과거이력 조회(모달용)
     */
    @GetMapping(value = "/vital-history/{id}")
    @Operation(summary = "바이탈 과거이력 조회(모달용)")
    public ResponseEntity<ApiResponse> selectVitalHistoryList(@PathVariable(name = "id") Long id){
        ResultResponse result = counselingService.selectVitalHistoryList(id);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    /**
     * 바이탈 정보 등록
     */
    @PostMapping(value = "/insert-vital/{id}")
    @Operation(summary = "바이탈 정보 등록")
    public ResponseEntity<ApiResponse> insertPatientVital(@PathVariable(name = "id") Long id, @RequestBody PatientVitalRequest[] patientVitalRequest){
        ResultResponse result = counselingService.insertPatientVital(id, patientVitalRequest);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    /**
     * 병원 상품 조회
     */
    @GetMapping(value = "/products-list")
    @Operation(summary = "병원 상품 조회",
            parameters = {
                    @Parameter(name = "searchValue", in = ParameterIn.QUERY, required = false, description = "사용자코드/시수술 상품명", example = "B00010012/심박기 거치술")
            })
    public ResponseEntity<ApiResponse> selectProducts(@Parameter(hidden = true) SearchProductsRequest searchProductsRequest) {
        ResultResponse result = counselingService.selectProducts(searchProductsRequest);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    /**
     * 방 리스트(셀렉트박스용) 조회
     */
    @GetMapping(value = "/room-list")
    @Operation(summary = "방 리스트 셀렉트박스")
    public ResponseEntity<ApiResponse> selectRoomList(@Parameter(hidden = true) Long hospitalId){
        ResultResponse result = counselingService.selectRoomList(hospitalId);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    /**
     * 대면상담 저장
     */
    @PostMapping(value = "/insert-counseling")
    @Operation(summary = "대면상담 저장")
    public ResponseEntity<ApiResponse> insertCounseling(@RequestBody CounselingRequest counselingRequest){
        ResultResponse result = counselingService.insertCounseling(counselingRequest);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    /**
     * 대면상담 이미지 업로드
     */
    @PostMapping(value = "/upload-counseling-images")
    @Operation(summary = "대면상담 이미지 업로드")
    public ResponseEntity<ApiResponse> uploadCounselingImages(@RequestBody CounselingRequest counselingRequest){
        ResultResponse result = counselingService.insertCounseling(counselingRequest);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    /**
     * 상담 차트이력 조회
     */
    @GetMapping(value = "/counseling-history/{id}")
    @Operation(summary = "상담 차트이력 조회")
    public ResponseEntity<ApiResponse> selectCounselingHistoryList(@PathVariable(name = "id") Long id){
        log.info("counselingHistoryController");
        ResultResponse result = counselingService.selectCounselingHistoryList(id);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    /**
     * 상담 내용 업데이트
     */
    @PutMapping(value = "/update-counseling/{id}")
    @Operation(summary = "상담 내용 업데이트")
    public ResponseEntity<ApiResponse> updateCounseling(@PathVariable(name = "id") Long id, @RequestBody UpdateCounselingRequest updateCounselingRequest){
        ResultResponse result = counselingService.updateCounselingContent(id, updateCounselingRequest);
        return ResponseEntity.ok(ApiResponse.success(result));
    }


}
