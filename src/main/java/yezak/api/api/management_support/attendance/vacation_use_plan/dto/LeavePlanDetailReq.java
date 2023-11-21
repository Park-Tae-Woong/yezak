package yezak.api.api.management_support.attendance.vacation_use_plan.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class LeavePlanDetailReq {
    @Hidden
    private Long hospitalId;
    @Schema(description = "연차사용계획 id", example = "1")
    private Long id;
}
