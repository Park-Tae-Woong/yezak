package yezak.api.api.management_support.attendance.vacation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class MyVacationReq {
    @Schema(description = "연도", example = "2023")
    private int year;
    @Schema(description = "유저아이디", example = "1")
    private Long id;
}
