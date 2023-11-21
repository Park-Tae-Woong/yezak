package yezak.api.api.management_support.attendance.vacation_use_plan;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import yezak.api.api.management_support.attendance.vacation_use_plan.dto.LeavePlanDetailReq;
import yezak.api.api.management_support.attendance.vacation_use_plan.dto.LeavePlanCheckReq;
import yezak.api.global.common.ResultResponse;
import yezak.api.util.Page;

import static yezak.api.config.MyIdConfig.myDepth3Id;
import static yezak.api.config.MyIdConfig.myHospitalId;

@RequiredArgsConstructor
@Component
@Service
@Slf4j
public class VacationUsePlanServiceImpl implements VacationUsePlanService {
    private final VacationUsePlanMapper vacationUsePlanMapper;
    private final int planId = 32;
    @Override
    public ResultResponse<?> checkVacationPlan(String searchValue, Integer year, Integer quarter, Integer pageNum, Integer pageSize) {
        if(!myDepth3Id().contains(planId)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
        int offset = (pageNum - 1) * pageSize;
        LeavePlanCheckReq leavePlanCheckReq = LeavePlanCheckReq.builder()
                .offset(offset)
                .pageSize(pageSize)
                .hospitalId(myHospitalId())
                .searchValue(searchValue)
                .year(year)
                .quarter(quarter)
                .build();

        int totalCount = vacationUsePlanMapper.countVacationPlan(leavePlanCheckReq);
        Page page = Page.builder()
                .pageNum(pageNum)
                .pageSize(pageSize)
                .totalCount(totalCount)
                .totalPage((int) Math.ceil((double)totalCount / pageSize))
                .build();

        return ResultResponse.builder()
                .data(vacationUsePlanMapper.checkVacationPlan(leavePlanCheckReq))
                .page(page)
                .result(true)
                .resultCode("200")
                .build();
    }

    @Override
    public ResultResponse<?> vacationPlanDetail(Long id) {
        if(!myDepth3Id().contains(planId)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
        LeavePlanDetailReq req = LeavePlanDetailReq.builder()
                .hospitalId(myHospitalId())
                .id(id)
                .build();
        return ResultResponse.builder()
                .data(vacationUsePlanMapper.vacationPlanDetail(req))
                .result(true)
                .resultCode("200")
                .build();
    }
}
