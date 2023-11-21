package yezak.api.api.management_support.attendance.vacation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class MyVacationRes {
    @Schema(description = "이름", example = "박태웅")
    private String name;
    @Schema(description = "입사일", example = "2022")
    private String joinedAt;
    @Schema(description = "유급휴가", example = "15")
    private double paidLeave;
    @Schema(description = "무급휴가", example = "20")
    private double unpaidLeave;
    @Schema(description = "총 사용가능한 연차", example = "35")
    private double total;
}
