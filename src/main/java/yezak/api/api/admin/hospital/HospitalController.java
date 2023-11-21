package yezak.api.api.admin.hospital;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yezak.api.api.admin.hospital.dto.OfficeHourRequest;
import yezak.api.api.admin.hospital.dto.OvertimeReq;
import yezak.api.api.admin.hospital.dto.UpdateHospitalInfoReq;
import yezak.api.api.admin.hospital.dto.VisitPurposeMulti;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;

import java.util.List;

@RequestMapping(value = "/api/hospital")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "Hospital-basic-setting", description = "병원관리 > 병원기초설정")

public class HospitalController {
    private final HospitalService hospitalService;

    //병원 정보
    @GetMapping(value = "/hospital-info")
    @Operation(summary = "요양기관 정보" , description = "요양기관 정보")
    public ResponseEntity<ApiResponse<?>> hospitalInfo() {
        ResultResponse<?> resultResponse = hospitalService.hospitalInfo();
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //병원 수정
    @PutMapping(value = "/update-hospital-info")
    @Operation(summary = "요양기관 수정" , description = "요양기관 수정")
    public ResponseEntity<ApiResponse<?>> updateUsage(@RequestBody UpdateHospitalInfoReq updateHospitalInfoReqs) {
        ResultResponse<?> resultResponse = hospitalService.updateHospitalInfo(updateHospitalInfoReqs);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //업무 시간 정보
    @GetMapping(value = "/office-hour")
    @Operation(summary = "업무시간 조회" , description = "업무시간 조회")
    public ResponseEntity<ApiResponse<?>> officeHourInfo(){
        ResultResponse<?> resultResponse = hospitalService.officeHourInfo();
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //근무 시간 수정
    @PutMapping(value = "/update-office-hour")
    @Operation(summary = "업무시간 수정" , description = "업무시간 수정")
    public ResponseEntity<ApiResponse<?>> updateOfficeHour(@RequestBody OfficeHourRequest officeHourReqs) {
        ResultResponse<?> resultResponse = hospitalService.updateOfficeHour(officeHourReqs);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //가산 시간
    @GetMapping(value = "/overtime-info")
    @Operation(summary = "가산시간 조회" , description = "가산시간 조회")
    public ResponseEntity<ApiResponse<?>> overtimeInfo(){
        ResultResponse<?> resultResponse = hospitalService.overtimeInfo();
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //가산 시간 수정
    @PutMapping(value = "/update-overtime")
    @Operation(summary = "가산시간 수정" , description = "가산시간 수정")
    public ResponseEntity<ApiResponse<?>> updateOvertime(@RequestBody List<OvertimeReq> overtimeReqs) {
        ResultResponse<?> resultResponse = hospitalService.updateOvertime(overtimeReqs);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //방문 목적
    @GetMapping(value = "/visit-purpose-info")
    @Operation(summary = "방문목적 조회")
    public ResponseEntity<ApiResponse<?>> visitPurposeInfo(){
        ResultResponse<?> resultResponse = hospitalService.visitPurposeInfo();
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }
    
    //방문목적 생성, 수정, 삭제
    @PostMapping(value = "/visit-purpose-multi")
    @Operation(summary = "방문목적 멀티처리" , description = "방문목적 멀티처리")
    public ResponseEntity<ApiResponse<?>> visitPurposeMulti(@RequestBody VisitPurposeMulti visitPurposeMulti) {
        ResultResponse<?> resultResponse = hospitalService.visitPurposeMulti(visitPurposeMulti);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

//    //방문목적 신규
//    @PostMapping(value = "/process-visit-purpose/regist")
//    @Operation(summary = "방문목적 신규" , description = "방문목적 신규")
//    public ResponseEntity<Void> processVisitPurposeRegist(@RequestBody InsertVisitPurposeReq insertVisitPurposeReq) {
//        hospitalService.processVisitPurposeRegist(insertVisitPurposeReq);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    //방문목적 수정
//    @PutMapping(value = "/process-visit-purpose/modify")
//    @Operation(summary = "방문목적 수정" , description = "방문목적 수정")
//    public ResponseEntity<Void> processVisitPurposeModify(@RequestBody List<UpdateVisitPurposeReq> updateVisitPurposeReqs) {
//        hospitalService.processVisitPurposeModify(updateVisitPurposeReqs);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    //방문목적 삭제
//    @DeleteMapping(value = "/process-visit-purpose/delete")
//    @Operation(summary = "방문목적 삭제" , description = "방문목적 삭제",
//            parameters = {
//                @Parameter(name = "id", description = "방문목적 id", example = "1")
//    })
//    public ResponseEntity<Void> processVisitPurposeDelete(@RequestParam Long id) {
//        hospitalService.processVisitPurposeDelete(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }


}
