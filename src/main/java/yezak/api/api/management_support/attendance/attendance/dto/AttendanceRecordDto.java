package yezak.api.api.management_support.attendance.attendance.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceRecordDto {
    private String month;
    @Schema(description = "이름", example = "박태웅")
    private String name;

    private String sunday;
    @Schema(description = "일요일 출근시간", example = "09:00")
    private String sunCommutingTime;
    @Schema(description = "일요일 퇴근시간", example = "18:00")
    private String sunLeavingTime;

    private String monday;
    @Schema(description = "월요일 출근시간", example = "09:00")
    private String monCommutingTime;
    @Schema(description = "월요일 퇴근시간", example = "18:00")
    private String monLeavingTime;

    private String tuesday;
    @Schema(description = "화요일 출근시간", example = "09:00")
    private String tueCommutingTime;
    @Schema(description = "화요일 퇴근시간", example = "18:00")
    private String tueLeavingTime;

    private String wednesday;
    @Schema(description = "수요일 출근시간", example = "09:00")
    private String wedCommutingTime;
    @Schema(description = "수요일 퇴근시간", example = "18:00")
    private String wedLeavingTime;

    private String thursday;
    @Schema(description = "목요일 출근시간", example = "09:00")
    private String thuCommutingTime;
    @Schema(description = "목요일 퇴근시간", example = "18:00")
    private String thuLeavingTime;

    private String friday;
    @Schema(description = "금요일 출근시간", example = "09:00")
    private String friCommutingTime;
    @Schema(description = "금요일 퇴근시간", example = "18:00")
    private String friLeavingTime;

    private String saturday;
    @Schema(description = "토요일 출근시간", example = "09:00")
    private String satCommutingTime;
    @Schema(description = "토요일 퇴근시간", example = "18:00")
    private String satLeavingTime;

}
