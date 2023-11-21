package yezak.api.api.management_support.attendance.vacation_manage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yezak.api.api.management_support.attendance.vacation.dto.VacationManageReq;
import yezak.api.api.management_support.attendance.vacation_manage.dto.*;
import yezak.api.global.common.ResultResponse;

import static yezak.api.config.MyIdConfig.myDepth3Id;
import static yezak.api.config.MyIdConfig.myHospitalId;

@RequiredArgsConstructor
@Component
@Service
@Slf4j
@Transactional
public class VacationManageServiceImpl implements VacationManageService{
    private final VacationManageMapper vacationManageMapper;
    private final int vacationManageId = 31;
    @Override
    public ResultResponse<?> vacationManageList(int year, String searchValue) {
        if(!myDepth3Id().contains(vacationManageId)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
        VacationManageReq vacationManageReq = VacationManageReq.builder()
                .hospitalId(myHospitalId())
                .year(year)
                .searchValue(searchValue)
                .build();

        return ResultResponse.builder()
                .data(vacationManageMapper.vacationManageList(vacationManageReq))
                .resultCode("200")
                .result(true)
                .build();

    }

    @Override
    public ResultResponse<?> nameList(String searchValue) {
        if(!myDepth3Id().contains(vacationManageId)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
        NameListReq nameListReq = NameListReq.builder()
                .hospitalId(myHospitalId())
                .searchValue(searchValue)
                .build();
        return ResultResponse.builder()
                .data(vacationManageMapper.nameList(nameListReq))
                .result(true)
                .resultCode("200")
                .build();
    }

    @Override
    public ResultResponse<?> increaseLeave(IncreaseLeaveReq increaseLeaveReq) {
        if(!myDepth3Id().contains(vacationManageId)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
        if(increaseLeaveReq.getLeave() % 0.5 != 0) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("400")
                    .resultMessage("0.5 단위로만 수정 가능합니다.")
                    .build();
        }
        if (increaseLeaveReq.getType() == 1) {
            IncreasePaidLeaveReq increasePaidLeaveReq = IncreasePaidLeaveReq.builder()
                    .paidLeave(increaseLeaveReq.getLeave())
                    .userId(increaseLeaveReq.getUserId())
                    .year(increaseLeaveReq.getYear())
                    .build();
            vacationManageMapper.increasePaidLeave(increasePaidLeaveReq);
        } else {

            IncreaseUnpaidLeaveReq increaseUnpaidLeaveReq = IncreaseUnpaidLeaveReq.builder()
                    .unpaidLeave(increaseLeaveReq.getLeave())
                    .userId(increaseLeaveReq.getUserId())
                    .year(increaseLeaveReq.getYear())
                    .build();
            vacationManageMapper.increaseUnpaidLeave(increaseUnpaidLeaveReq);
        }
        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .resultMessage("수정되었습니다.")
                .build();
    }

    @Override
    public ResultResponse<?> decreaseLeave(DecreaseLeaveReq decreaseLeaveReq) {
        if(!myDepth3Id().contains(vacationManageId)) {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
        CheckLeaveReq checkLeaveReq = new CheckLeaveReq(decreaseLeaveReq.getUserId(), decreaseLeaveReq.getYear());
        if (decreaseLeaveReq.getType() == 1) {
            double paid = vacationManageMapper.checkPaidLeave(checkLeaveReq);
            if (paid < decreaseLeaveReq.getLeave()){
                return ResultResponse.builder()
                        .result(false)
                        .resultCode("400")
                        .resultMessage("회수할 휴가가 부족합니다. 기간을 조정해주세요.")
                        .build();
            }
            DecreasePaidLeaveReq decreasePaidLeaveReq = DecreasePaidLeaveReq.builder()
                    .paidLeave(decreaseLeaveReq.getLeave())
                    .userId(decreaseLeaveReq.getUserId())
                    .year(decreaseLeaveReq.getYear())
                    .build();
            vacationManageMapper.decreasePaidLeave(decreasePaidLeaveReq);
        } else {
            double unpaid = vacationManageMapper.checkUnpaidLeave(checkLeaveReq);
            if (unpaid < decreaseLeaveReq.getLeave()){
                return ResultResponse.builder()
                        .result(false)
                        .resultCode("400")
                        .resultMessage("회수할 휴가가 부족합니다. 기간을 조정해주세요.")
                        .build();
            }
            DecreaseUnpaidLeaveReq decreaseUnpaidLeaveReq = DecreaseUnpaidLeaveReq.builder()
                    .unpaidLeave(decreaseLeaveReq.getLeave())
                    .userId(decreaseLeaveReq.getUserId())
                    .year(decreaseLeaveReq.getYear())
                    .build();
            vacationManageMapper.decreaseUnpaidLeave(decreaseUnpaidLeaveReq);
        }
        return ResultResponse.builder()
                .result(true)
                .resultCode("200")
                .resultMessage("수정되었습니다.")
                .build();
    }
}
