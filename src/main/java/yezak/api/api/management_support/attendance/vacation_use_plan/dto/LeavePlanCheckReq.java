package yezak.api.api.management_support.attendance.vacation_use_plan.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class LeavePlanCheckReq {
    @Schema(description = "이름", example = "박태웅")
    private String searchValue;
    @Schema(description = "년도", example = "2023")
    private Integer year;
    @Schema(description = "분기", example = "1")
    private Integer quarter;
    @Hidden
    private Long hospitalId;
    private Integer offset;
    private Integer pageSize;
}
