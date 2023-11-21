package yezak.api.api.management_support.attendance.vacation_use_plan.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class LeavePlanDetailRes {
    @Schema(description = "분기", example = "1")
    private String quarter;
    @Schema(description = "사용일수", example = "2")
    private String days;
    @Schema(description = "날짜", example = "2023.06.15 - 2023.06.17")
    private String date;
    @Schema(description = "이름", example = "박태웅")
    private String name;
}
