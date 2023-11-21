package yezak.api.api.management_support.attendance.attendance;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import yezak.api.api.management_support.attendance.attendance.dto.AttendanceSearchDto;
import yezak.api.global.common.ResultResponse;

import static yezak.api.config.MyIdConfig.myDepth3Id;
import static yezak.api.config.MyIdConfig.myHospitalId;


@RequiredArgsConstructor
@Component
@Service
@Slf4j
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceMapper attendanceMapper;
    private final Integer attendanceId = 29;

    @Override
    public ResultResponse<?> attendanceRecord(String name, String start, String end) {
        if(myDepth3Id().contains(attendanceId)) {
            AttendanceSearchDto attendanceSearchDto = new AttendanceSearchDto(name, start, end, myHospitalId());
            return ResultResponse.builder()
                    .data(attendanceMapper.attendanceRecord(attendanceSearchDto))
                    .result(true)
                    .resultCode("200")
                    .build();
        } else {
            return ResultResponse.builder()
                    .result(false)
                    .resultCode("401")
                    .resultMessage("권한이 없습니다.")
                    .build();
        }
    }

}
