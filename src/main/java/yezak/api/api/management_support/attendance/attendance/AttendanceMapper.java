package yezak.api.api.management_support.attendance.attendance;

import org.apache.ibatis.annotations.Mapper;
import yezak.api.api.management_support.attendance.attendance.dto.AttendanceSearchDto;
import yezak.api.api.management_support.attendance.attendance.dto.AttendanceRecordDto;
import yezak.api.api.management_support.attendance.attendance.dto.CreateAttendanceDto;

import java.util.List;

@Mapper
public interface AttendanceMapper {

    List<AttendanceRecordDto> attendanceRecord(AttendanceSearchDto attendanceSearchDto);

    void createAttendance(CreateAttendanceDto createAttendanceDtos);
}
