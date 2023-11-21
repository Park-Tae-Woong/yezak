package yezak.api.api.management_support.attendance.vacation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class VacationManageReq {
    @Schema(description = "연도", example = "2023")
    private int year;
    @Schema(description = "병원Id", example = "1", hidden = true)
    private Long hospitalId;
    @Schema(description = "이름", example = "박태웅")
    private String searchValue;
}
