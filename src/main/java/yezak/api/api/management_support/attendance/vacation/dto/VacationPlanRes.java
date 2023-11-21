package yezak.api.api.management_support.attendance.vacation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class VacationPlanRes {
    @Schema(description = "분기", example = "2023년 1분기")
    private String quarter;
    @Schema(description = "일수", example = "2일")
    private String days;
    @Schema(description = "사용계획 등록일", example = "2023.01.02")
    private String createdAt;
    private Long id;
}
