package yezak.api.api.management_support.attendance.vacation_use_plan.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class LeavePlanCheckRes {
    @Schema(description = "이름", example = "박태웅")
    private String name;
    @Schema(description = "분기", example = "1")
    private String quarter;
    @Schema(description = "사용일수", example = "2")
    private String days;
}
