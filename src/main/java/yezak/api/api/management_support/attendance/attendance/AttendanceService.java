package yezak.api.api.management_support.attendance.attendance;

import yezak.api.global.common.ResultResponse;

public interface AttendanceService {

    ResultResponse<?> attendanceRecord(String name, String start, String end);

}
