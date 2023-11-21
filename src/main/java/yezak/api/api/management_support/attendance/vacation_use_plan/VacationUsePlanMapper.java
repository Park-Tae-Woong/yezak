package yezak.api.api.management_support.attendance.vacation_use_plan;

import org.apache.ibatis.annotations.Mapper;
import yezak.api.api.management_support.attendance.vacation_use_plan.dto.LeavePlanCheckReq;
import yezak.api.api.management_support.attendance.vacation_use_plan.dto.LeavePlanCheckRes;
import yezak.api.api.management_support.attendance.vacation_use_plan.dto.LeavePlanDetailReq;
import yezak.api.api.management_support.attendance.vacation_use_plan.dto.LeavePlanDetailRes;

import java.util.List;
@Mapper
public interface VacationUsePlanMapper {
    List<LeavePlanCheckRes> checkVacationPlan(LeavePlanCheckReq leavePlanCheckReq);
    int countVacationPlan(LeavePlanCheckReq leavePlanCheckReq);

    LeavePlanDetailRes vacationPlanDetail(LeavePlanDetailReq leavePlanDetailReq);
}
