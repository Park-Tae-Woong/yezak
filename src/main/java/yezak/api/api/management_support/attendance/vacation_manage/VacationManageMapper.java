package yezak.api.api.management_support.attendance.vacation_manage;

import org.apache.ibatis.annotations.Mapper;
import yezak.api.api.management_support.attendance.vacation.dto.MyVacationRes;
import yezak.api.api.management_support.attendance.vacation.dto.VacationManageReq;
import yezak.api.api.management_support.attendance.vacation_manage.dto.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface VacationManageMapper {
    List<MyVacationRes> vacationManageList(VacationManageReq vacationManageReq);
    List<Map<String, Object>> nameList(NameListReq nameListReq);
    void increasePaidLeave(IncreasePaidLeaveReq increasePaidLeaveReq);
    void increaseUnpaidLeave(IncreaseUnpaidLeaveReq increaseUnpaidLeaveReq);

    void decreasePaidLeave(DecreasePaidLeaveReq decreasePaidLeaveReq);
    void decreaseUnpaidLeave(DecreaseUnpaidLeaveReq decreaseUnpaidLeaveReq);

    double checkPaidLeave(CheckLeaveReq checkLeaveReq);
    double checkUnpaidLeave(CheckLeaveReq checkLeaveReq);

}
