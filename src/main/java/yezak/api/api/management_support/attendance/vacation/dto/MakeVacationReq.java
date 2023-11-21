package yezak.api.api.management_support.attendance.vacation.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class MakeVacationReq {
    @Hidden
    @Schema(description = "유저id", example = "1")
    private Long userId;
    @Schema(description = "유급/무급", example = "1")
    private int isPaidLeave;
    @Schema(description = "반차인지 아닌지", example = "1이면 반차")
    private int isHalfOff;
    @Schema(description = "시간", example = "2023-02-02 09:00:00")
    private String startAt;
    @Schema(description = "시작일", example = "2023-02-02")
    private String start;
    @Schema(description = "종료일", example = "2023-02-04")
    private String end;

}
