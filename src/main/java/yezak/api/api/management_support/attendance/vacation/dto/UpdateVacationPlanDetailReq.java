package yezak.api.api.management_support.attendance.vacation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class UpdateVacationPlanDetailReq {
    @Schema(description = "연차사용계획id", example = "1")
    private Long vacationUsePlanId;
    @Schema(description = "시작", example = "2023-02-02")
    private String start;
    @Schema(description = "종료", example = "2023-02-04")
    private String end;
}
