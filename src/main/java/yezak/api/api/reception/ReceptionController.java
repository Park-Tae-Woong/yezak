package yezak.api.api.reception;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yezak.api.api.reception.dto.*;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;

import java.util.List;

@RequestMapping(value = "/api/reception")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "접수/수납", description = "접수/수납")
public class ReceptionController {

    private final ReceptionService service;

    @Operation(summary = "공단 api 연동 수진자 조회")
    @PostMapping(value = "/search-sujinja-info")
    public ResponseEntity<ApiResponse> selectUserInfoTest(@RequestBody List<ReceptionSujinjaSearchRequest> request){
        ResultResponse result = service.selectUserInfoTest(request);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @Operation(summary = "환자 검색",
            parameters = {
                    @Parameter(name = "searchKeyword", description = "검색어", example = "홍길동")
            })
    @GetMapping(value = "/search-patients-info")
    public ResponseEntity<ApiResponse> selectPatientsInfo(@RequestParam String searchKeyword){
        ResultResponse result = service.selectPatientsInfo(searchKeyword);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @Operation(summary = "금일 접수 현황 리스트 조회")
    @GetMapping(value = "/search-today-reception-list")
    public ResponseEntity<ApiResponse> selectTodayReceptionList(@RequestParam(required = false) String gubun){
        ResultResponse result = service.selectTodayReceptionList(gubun);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @Operation(summary = "대기실 리스트 조회")
    @GetMapping(value = "/search-room-list")
    public ResponseEntity<ApiResponse> selectRoomList(){
        ResultResponse result = service.selectRoomList();
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @Operation(summary = "병원 현황 리스트 조회")
    @GetMapping(value = "/search-today-hospital-state-list")
    public ResponseEntity<ApiResponse> selectTodayHospitalStateList(Long roomId){
        ResultResponse result = service.selectTodayHospitalStateList(roomId);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @Operation(summary = "대기실 변경")
    @PutMapping(value = "/update-room")
    public ResponseEntity<ApiResponse> updateRoom(UpdateRoomReq updateRoomReq){
        ResultResponse result = service.updateRoom(updateRoomReq);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @Operation(summary = "병원 밖으로")
    @PutMapping(value = "/out")
    public ResponseEntity<ApiResponse> outHospital(Long id){
        ResultResponse result = service.outHospital(id);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @Operation(summary = "상태 변경")
    @PutMapping(value = "/update-status")
    public ResponseEntity<ApiResponse> updateStatus(UpdateStatusReq updateStatusReq){
        ResultResponse result = service.updateStatus(updateStatusReq);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @Operation(summary = "접수 취소")
    @DeleteMapping(value = "/delete")
    public ResponseEntity<ApiResponse> deleteReception(Long id){
        ResultResponse result = service.deleteReception(id);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @Operation(summary = "접수 대기")
    @GetMapping(value = "/wait-reception")
    public ResponseEntity<ApiResponse> waitReceptionList(){
        ResultResponse result = service.waitReceptionList();
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @Operation(summary = "환자 등록")
    @PostMapping(value = "/new-patient")
    public ResponseEntity<ApiResponse> newPatient(@RequestBody NewPatientReq newPatientReq){
        ResultResponse result = service.newPatient(newPatientReq);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @Operation(summary = "최근접수기록")
    @GetMapping(value = "/recent-reception")
    public ResponseEntity<ApiResponse> recentReceptionRecord(@RequestParam Long patientId, @RequestParam(required = false) String start, @RequestParam(required = false) String end){
        ResultResponse result = service.recentReceptionRecord(patientId, start, end);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @Operation(summary = "인적 정보",
            parameters = {
                    @Parameter(name = "patientId", description = "환자 id", example = "1")
            })
    @GetMapping(value = "/patients-info")
    public ResponseEntity<ApiResponse> patientInfo(@RequestParam Long patientId){
        ResultResponse result = service.patientInfo(patientId);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @Operation(summary = "접수 정보",
            parameters = {
                    @Parameter(name = "receptionId", description = "접수 id", example = "1")
            })
    @GetMapping(value = "/reception-info")
    public ResponseEntity<ApiResponse> receptionInfo(@RequestParam Long receptionId){
        ResultResponse result = service.receptionInfo(receptionId);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @Operation(summary = "예약 정보",
            parameters = {
                    @Parameter(name = "patientId", description = "환자 id", example = "1")
            })
    @GetMapping(value = "/reservation-info")
    public ResponseEntity<ApiResponse> reservationInfo(@RequestParam Long patientId){
        ResultResponse result = service.reservationInfo(patientId);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @Operation(summary = "인적정보 수정")
    @PutMapping(value = "/update-patient-info")
    public ResponseEntity<ApiResponse> updatePatientInfo(@RequestBody UpdatePatientInfoReq updatePatientInfoReq){
        ResultResponse result = service.updatePatientInfo(updatePatientInfoReq);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @Operation(summary = "접수정보 수정")
    @PutMapping(value = "/update-reception-info")
    public ResponseEntity<ApiResponse> updateReceptionInfo(@RequestBody UpdateReceptionInfoReq updateReceptionInfoReq){
        ResultResponse result = service.updateReceptionInfo(updateReceptionInfoReq);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @Operation(summary = "수정이력",
            parameters = {
                    @Parameter(name = "patientId", description = "환자 id", example = "1")
            })
    @GetMapping(value = "/patient-log")
    public ResponseEntity<ApiResponse> patientLogList(@RequestParam Long patientId){
        ResultResponse result = service.patientLogList(patientId);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @Operation(summary = "고객 메모 생성")
    @PostMapping(value = "/insert-memo")
    public ResponseEntity<ApiResponse> insertPatientMemo(@RequestBody InsertPatientMemoReq insertPatientMemoReq){
        ResultResponse result = service.insertPatientMemo(insertPatientMemoReq);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @Operation(summary = "메모 삭제",
            parameters = {
                    @Parameter(name = "memoId", description = "메모 id", example = "1")
            })
    @DeleteMapping(value = "/delete-memo")
    public ResponseEntity<ApiResponse> deletePatientMemo(@RequestParam Long memoId){
        ResultResponse result = service.deletePatientMemo(memoId);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @Operation(summary = "고객 메모 수정")
    @PutMapping(value = "/update-memo")
    public ResponseEntity<ApiResponse> updatePatientMemo(@RequestBody UpdatePatientMemoReq updatePatientMemoReq){
        ResultResponse result = service.updatePatientMemo(updatePatientMemoReq);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @Operation(summary = "메모 리스트",
            parameters = {
                    @Parameter(name = "patientId", description = "환자 id", example = "37")
            })
    @GetMapping(value = "/memo-list")
    public ResponseEntity<ApiResponse> patientMemoList(@RequestParam Long patientId){
        ResultResponse result = service.patientMemoList(patientId);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

}
