package yezak.api.api.management_support.attendance.vacation_manage;

import yezak.api.api.management_support.attendance.vacation_manage.dto.DecreaseLeaveReq;
import yezak.api.api.management_support.attendance.vacation_manage.dto.IncreaseLeaveReq;
import yezak.api.global.common.ResultResponse;

public interface VacationManageService {
    ResultResponse<?> vacationManageList(int year, String searchValue);
    ResultResponse<?> nameList(String searchValue);
    ResultResponse<?> increaseLeave(IncreaseLeaveReq increaseLeaveReq);
    ResultResponse<?> decreaseLeave(DecreaseLeaveReq decreaseLeaveReq);

}
