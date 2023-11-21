package yezak.api.api.management_support.attendance.vacation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yezak.api.api.management_support.attendance.vacation.dto.MakeVacationReq;
import yezak.api.api.management_support.attendance.vacation.dto.UpdateVacationPlanDto;
import yezak.api.api.management_support.attendance.vacation.dto.VacationPlanDto;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;

@RequestMapping(value = "/api/vacation")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "vacation", description = "근태관리 > 휴가현황")
public class VacationController {

    private final VacationService vacationService;

    //휴가현황
    @GetMapping(value = "/my-vacation")
    @Operation(summary = "휴가 보유 현황" , description = "휴가 보유 현황",
            parameters = {
                    @Parameter(name = "year", description = "조회할 연도", example = "2022")})
    public ResponseEntity<ApiResponse<?>> myVacation(@RequestParam int year){
        ResultResponse<?> resultResponse = vacationService.myVacation(year);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //연차사용계획
    @GetMapping(value = "/vacation-plan")
    @Operation(summary = "연차 사용 계획" , description = "연차 사용 계획",
            parameters = {
                    @Parameter(name = "year", description = "조회할 연도", example = "2022")
    })
    public ResponseEntity<ApiResponse<?>> vacationPlan(@RequestParam Integer year){
        ResultResponse<?> resultResponse = vacationService.vacationPlan(year);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //사용 기록
    @GetMapping(value = "/vacation-record")
    @Operation(summary = "사용 기록" , description = "사용 기록",
            parameters = {
                    @Parameter(name = "year", description = "조회할 연도", example = "2022")})
    public ResponseEntity<ApiResponse<?>> vacationRecord(@RequestParam Integer year){
        ResultResponse<?> resultResponse = vacationService.vacationRecord(year);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //휴가 삭제
    @DeleteMapping(value = "/delete-vacation")
    @Operation(summary = "사용 기록 삭제" , description = "사용 기록 삭제",
            parameters = {
                    @Parameter(name = "id", description = "삭제할 휴가 id", example = "1")})
    public ResponseEntity<ApiResponse<?>> deleteVacation(@RequestParam Long id){
        ResultResponse<?> resultResponse = vacationService.deleteVacation(id);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }


    //휴가 생성
    @PostMapping(value = "/make-vacation")
    @Operation(summary = "휴가 등록" , description = "휴가 등록")
    public ResponseEntity<ApiResponse<?>> makeVacation(@RequestBody MakeVacationReq makeVacationReq){
        ResultResponse<?> resultResponse = vacationService.makeVacation(makeVacationReq);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //연차사용계획 생성
    @PostMapping(value = "/make-vacation-plan")
    @Operation(summary = "연차 사용 계획 등록" , description = "연차 사용 계획 등록")
    public ResponseEntity<ApiResponse<?>> makeVacationPlan(@RequestBody VacationPlanDto vacationPlanDto){
        ResultResponse<?> resultResponse = vacationService.makeVacationPlan(vacationPlanDto);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //연차사용계획 수정
    @PostMapping(value = "/update-vacation-plan")
    @Operation(summary = "연차 사용 계획 수정" , description = "연차 사용 계획 수정")
    public ResponseEntity<ApiResponse<?>> updateVacationPlan(@RequestBody UpdateVacationPlanDto updateVacationPlanDto){
        ResultResponse<?> resultResponse = vacationService.updateVacationPlan(updateVacationPlanDto);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }


}
