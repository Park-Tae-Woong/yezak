package yezak.api.api.management_support.attendance.vacation.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class VacationRecordReq {
    @Schema(description = "연도", example = "2023")
    int year;
    @Hidden
    @Schema(description = "유저id", example = "1")
    Long userId;
}
