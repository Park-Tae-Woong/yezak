package yezak.api.api.management_support.attendance.vacation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yezak.api.api.management_support.attendance.vacation.dto.*;
import yezak.api.global.common.ResultResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static yezak.api.config.MyIdConfig.myDepth3Id;
import static yezak.api.config.MyIdConfig.myUserId;

@RequiredArgsConstructor
@Component
@Service
@Slf4j
@Transactional
public class VacationServiceImpl implements VacationService{

    private final VacationMapper vacationMapper;
    private final int vacationId = 30;
    @Override
    public ResultResponse<?> myVacation(Integer year) {
        if(!myDepth3Id().contains(vacationId)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
        MyVacationReq myVacationReq = MyVacationReq.builder()
                .id(myUserId())
                .year(year)
                .build();
        return ResultResponse.builder()
                .data(vacationMapper.myVacation(myVacationReq))
                .resultCode("200")
                .result(true)
                .build();
    }

    @Override
    public ResultResponse<?> vacationPlan(Integer year) {
        if(!myDepth3Id().contains(vacationId)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
        MyVacationReq myVacationReq = MyVacationReq.builder()
                .year(year)
                .id(myUserId())
                .build();
        return ResultResponse.builder()
                .data(vacationMapper.vacationPlan(myVacationReq))
                .result(true)
                .resultCode("200")
                .build();
    }

    @Override
    public ResultResponse<?> vacationRecord(Integer year) {
        if(!myDepth3Id().contains(vacationId)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
        VacationRecordReq vacationRecordReq = VacationRecordReq.builder()
                .userId(myUserId())
                .year(year)
                .build();
        return ResultResponse.builder()
                .data(vacationMapper.vacationRecord(vacationRecordReq))
                .result(true)
                .resultCode("200")
                .build();
    }

    @Override
    public ResultResponse<?> deleteVacation(Long id) {
        if(!myDepth3Id().contains(vacationId)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
        int checkLeave = vacationMapper.checkLeave(id);
        double day = vacationMapper.calculateDay(id);
        //1이면 유급휴가 2면 무급휴가
        if (checkLeave == 1){
            IncreasePaidLeaveReq increasePaidLeaveReq = IncreasePaidLeaveReq.builder()
                    .paidLeave(day)
                    .userId(myUserId())
                    .year(LocalDate.now().getYear())
                    .build();
            vacationMapper.increasePaidLeave(increasePaidLeaveReq);

        } else {
            IncreaseUnpaidLeaveReq increaseUnpaidLeaveReq = IncreaseUnpaidLeaveReq.builder()
                    .unpaidLeave(day)
                    .userId(myUserId())
                    .year(LocalDate.now().getYear())
                    .build();
            vacationMapper.increaseUnpaidLeave(increaseUnpaidLeaveReq);

        }
        vacationMapper.deleteVacation(id);
        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .resultMessage("삭제되었습니다.")
                .build();
    }

    @Override
    public ResultResponse<?> makeVacation(MakeVacationReq makeVacationReq) {
        if(!myDepth3Id().contains(vacationId)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
        MakeVacationReq req;
        if(makeVacationReq.getIsHalfOff() == 1) {
            req = MakeVacationReq.builder()
                    .startAt(makeVacationReq.getStartAt())
                    .isPaidLeave(makeVacationReq.getIsPaidLeave())
                    .userId(myUserId())
                    .build();

            DecreaseHalfLeaveReq decreaseHalfLeaveReq = DecreaseHalfLeaveReq.builder()
                    .userId(myUserId())
                    .year(LocalDateTime.now().getYear())
                    .build();
            if(makeVacationReq.getIsPaidLeave() == 1){
                vacationMapper.decreaseHalfPaidLeave(decreaseHalfLeaveReq);
            } else {
                vacationMapper.decreaseHalfUnpaidLeave(decreaseHalfLeaveReq);
            }
        } else {
            req = MakeVacationReq.builder()
                    .start(makeVacationReq.getStart())
                    .end(makeVacationReq.getEnd())
                    .isPaidLeave(makeVacationReq.getIsPaidLeave())
                    .userId(myUserId())
                    .build();
            DecreaseLeaveReq decreaseLeaveReq = DecreaseLeaveReq.builder()
                    .year(LocalDateTime.now().getYear())
                    .start(makeVacationReq.getStart())
                    .end(makeVacationReq.getEnd())
                    .userId(myUserId())
                    .build();

            if(makeVacationReq.getIsPaidLeave() == 1){
                vacationMapper.decreasePaidLeave(decreaseLeaveReq);
            } else {
                vacationMapper.decreaseUnpaidLeave(decreaseLeaveReq);
            }
        }
        vacationMapper.makeVacation(req);

//        if (vacationRecordDto.getIsPaidLeave() == 1) {
//            int i = vacationMapper.countUnpaidLeave(vacationRecordDto);
//            if (i <= 0) {
//                throw new IllegalArgumentException("무급 휴가 없음");
//            } else {
//                vacationMapper.decreaseUnpaidLeave(vacationRecordDto);
//            }
//        } else {
//            int j = vacationMapper.countPaidLeave(vacationRecordDto);
//            if (j <= 0) {
//                throw new IllegalArgumentException("유급 휴가 없음");
//            } else {
//                vacationMapper.decreasePaidLeave(vacationRecordDto);
//            }
//        }
        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .resultMessage("등록되었습니다.")
                .build();
    }

    @Override
    public ResultResponse<?> makeVacationPlan(VacationPlanDto vacationPlanDto) {
        if(!myDepth3Id().contains(vacationId)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
        if (vacationMapper.findByYearAndQuarter(vacationPlanDto.getVacationPlanReq().getYear(), vacationPlanDto.getVacationPlanReq().getQuarter()) != null){
            return ResultResponse.builder()
                    .resultMessage("연도와 분기가 이미 존재합니다.")
                    .result(false)
                    .resultCode("200")
                    .build();
        }
        VacationPlanReq vacationPlanReq = VacationPlanReq.builder()
                .userId(myUserId())
                .year(vacationPlanDto.getVacationPlanReq().getYear())
                .quarter(vacationPlanDto.getVacationPlanReq().getQuarter())
                .build();
            vacationMapper.makeVacationPlan(vacationPlanReq);
        Long vacId = vacationPlanReq.getId();

        List<VacationPlanDetailDto> vacationPlanDetailDtoList = vacationPlanDto.getVacationPlanDetailDtoList();
        if (vacationPlanDetailDtoList != null) {
            for (VacationPlanDetailDto vacationPlanDetailDto : vacationPlanDetailDtoList){
                vacationPlanDetailDto = VacationPlanDetailDto.builder()
                        .vacationUsePlanId(vacId)
                        .start(vacationPlanDetailDto.getStart())
                        .end(vacationPlanDetailDto.getEnd())
                        .build();
                vacationMapper.makeVacationPlanDetail(vacationPlanDetailDto);
            }
        }

        return ResultResponse.builder()
                .resultMessage("등록되었습니다.")
                .result(true)
                .resultCode("200")
                .build();
    }

    @Override
    public ResultResponse<?> updateVacationPlan(UpdateVacationPlanDto updateVacationPlanDto) {
        if(!myDepth3Id().contains(vacationId)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
        List<UpdateVacationPlanDetailReq> updateVacationPlanDetailReqList = updateVacationPlanDto.getUpdateVacationPlanDetailReqList();
        List<Long> deleteIds = updateVacationPlanDto.getDeleteIds();
        if (updateVacationPlanDetailReqList != null) {
            for (UpdateVacationPlanDetailReq updateVacationPlanDetailReq : updateVacationPlanDetailReqList){
                updateVacationPlanDetailReq = UpdateVacationPlanDetailReq.builder()
                        .vacationUsePlanId(updateVacationPlanDetailReq.getVacationUsePlanId())
                        .start(updateVacationPlanDetailReq.getStart())
                        .end(updateVacationPlanDetailReq.getEnd())
                        .build();
                vacationMapper.updateVacationPlanDetail(updateVacationPlanDetailReq);
            }
        }
        if (deleteIds != null) {
            for (Long deleteId : deleteIds) {
                vacationMapper.deleteVacationPlan(deleteId);
            }
        }
        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .resultMessage("수정되었습니다.")
                .build();

    }

}
