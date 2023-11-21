package yezak.api.api.management_support.attendance.vacation_use_plan;

import yezak.api.global.common.ResultResponse;

public interface VacationUsePlanService {
    ResultResponse<?> checkVacationPlan(String searchValue, Integer year, Integer quarter, Integer pageNum, Integer pageSize);
    ResultResponse<?> vacationPlanDetail(Long id);
}
