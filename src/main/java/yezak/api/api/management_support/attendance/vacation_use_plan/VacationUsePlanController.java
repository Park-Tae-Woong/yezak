package yezak.api.api.management_support.attendance.vacation_use_plan;

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

@RequestMapping(value = "/api/vacation_use_plan")
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "Vacation-plan", description = "근태관리 > 연차사용계획 확인")
public class VacationUsePlanController {
    private final VacationUsePlanService vacationUsePlanService;
    //연차사용계획 확인
    @GetMapping(value = "/list")
    @Operation(summary = "연차사용계획 리스트" , description = "연차사용계획 리스트")
    public ResponseEntity<ApiResponse<?>> checkVacationPlan(@RequestParam(required = false) String searchValue,
                                                    @RequestParam Integer year,
                                                    @RequestParam(required = false) Integer quarter,
                                                    @RequestParam Integer pageNum,
                                                    @RequestParam Integer pageSize){
        ResultResponse<?> resultResponse = vacationUsePlanService.checkVacationPlan(searchValue, year, quarter, pageNum, pageSize);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }

    //연차사용계획상세
    @GetMapping(value = "/detail")
    @Operation(summary = "연차사용계획 상세" , description = "연차사용계획 상세")
    public ResponseEntity<ApiResponse<?>> vacationPlanDetail(@RequestParam Long id){
        ResultResponse<?> resultResponse = vacationUsePlanService.vacationPlanDetail(id);
        return ResponseEntity.ok(ApiResponse.success(resultResponse));
    }
}
