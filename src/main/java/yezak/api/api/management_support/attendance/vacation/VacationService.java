package yezak.api.api.management_support.attendance.vacation;

import yezak.api.api.management_support.attendance.vacation.dto.MakeVacationReq;
import yezak.api.api.management_support.attendance.vacation.dto.UpdateVacationPlanDto;
import yezak.api.api.management_support.attendance.vacation.dto.VacationPlanDto;
import yezak.api.global.common.ResultResponse;

public interface VacationService {
    ResultResponse<?> myVacation(Integer year);

    ResultResponse<?> vacationPlan(Integer year);

    ResultResponse<?> vacationRecord(Integer year);

    ResultResponse<?> deleteVacation(Long id);

    ResultResponse<?> makeVacation(MakeVacationReq makeVacationReq);

    ResultResponse<?> makeVacationPlan(VacationPlanDto vacationPlanDto);
    ResultResponse<?> updateVacationPlan(UpdateVacationPlanDto updateVacationPlanDto);





}
