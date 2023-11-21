package yezak.api.api.management_support.attendance.vacation;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import yezak.api.api.management_support.attendance.vacation.dto.*;

import java.util.List;

@Mapper
public interface VacationMapper {
    MyVacationRes myVacation(MyVacationReq myVacationReq);
    List<VacationPlanRes> vacationPlan(MyVacationReq myVacationReq);
    List<VacationRecordRes> vacationRecord(VacationRecordReq vacationRecordReq);
    void deleteVacation(Long id);
    void increasePaidLeave(IncreasePaidLeaveReq increasePaidLeaveReq);
    void increaseUnpaidLeave(IncreaseUnpaidLeaveReq increaseUnpaidLeaveReq);
    int checkLeave(Long id);
    double calculateDay(Long id);
    void makeVacation(MakeVacationReq makeVacationReq);
    void decreasePaidLeave(DecreaseLeaveReq decreaseLeaveReq);
    void decreaseUnpaidLeave(DecreaseLeaveReq decreaseLeaveReq);
    void decreaseHalfPaidLeave(DecreaseHalfLeaveReq decreaseHalfLeaveReq);
    void decreaseHalfUnpaidLeave(DecreaseHalfLeaveReq decreaseHalfLeaveReq);

    void makeVacationPlan(VacationPlanReq vacationPlanReq);
    void makeVacationPlanDetail(VacationPlanDetailDto vacationPlanDetailDto);
    void deleteVacationPlan(Long deleteId);
    void updateVacationPlanDetail(UpdateVacationPlanDetailReq updateVacationPlanDetailReq);
    Long findByYearAndQuarter(@Param("year") int year, @Param("quarter") int quarter);


}
