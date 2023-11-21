package yezak.api.api.management_support.attendance.vacation.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class VacationPlanReq {
    @Hidden
    private Long id;
    @Hidden
    @Schema(description = "유저id", example = "1")
    private Long userId;
    @Schema(description = "년도", example = "2023")
    private int year;
    @Schema(description = "분기", example = "1")
    private int quarter;
}
