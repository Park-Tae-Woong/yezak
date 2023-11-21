package yezak.api.api.management_support.attendance.vacation_manage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yezak.api.api.management_support.attendance.vacation_manage.dto.DecreaseLeaveReq;
import yezak.api.api.management_support.attendance.vacation_manage.dto.IncreaseLeaveReq;
import yezak.api.global.common.ApiResponse;
import yezak.api.global.common.ResultResponse;

@RequestMapping(value = "/api/vacation_manage")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "vacation_manage", description = "경영지원 > 근태관리 > 휴가관리")
public class VacationManageController {
    private final VacationManageService vacationManageService;
    //휴가관리
    @GetMapping(value = "/list")
    @Operation(summary = "휴가관리 리스트" , description = "휴가관리 리스트",
            parameters = {
                    @Parameter(name = "year", description = "조회 연도", example = "2023"),
                    @Parameter(name = "searchValue", description = "검색할 이름", example = "박")
            })
    public ResponseEntity<ApiResponse<?>> vacationManage(@RequestParam int year,
                                                      @RequestParam(required = false) String searchValue){
        ResultResponse<?> resultResponse = vacationManageService.vacationManageList(year, searchValue);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @PutMapping(value = "/increase")
    @Operation(summary = "휴가 부여" , description = "휴가 부여 leave(1 = 유급, 2 = 무급)")
    public ResponseEntity<ApiResponse<?>> vacationManage(@RequestBody IncreaseLeaveReq increaseLeaveReq){
        ResultResponse<?> resultResponse = vacationManageService.increaseLeave(increaseLeaveReq);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @PutMapping(value = "/decrease")
    @Operation(summary = "휴가 회수" , description = "휴가 회수 leave(1 = 유급, 2 = 무급)")
    public ResponseEntity<ApiResponse<?>> decreaseLeave(@RequestBody DecreaseLeaveReq decreaseLeaveReq){
        ResultResponse<?> resultResponse = vacationManageService.decreaseLeave(decreaseLeaveReq);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    @GetMapping(value = "/user_ist")
    @Operation(summary = "유저 리스트" , description = "유저 리스트",
            parameters = {
            @Parameter(name = "searchValue", description = "검색할 이름", example = "박")
            })
    public ResponseEntity<ApiResponse<?>> nameList(@RequestParam(required = false) String searchValue){
        ResultResponse<?> resultResponse = vacationManageService.nameList(searchValue);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }
}
