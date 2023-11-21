package yezak.api.api.management_support.attendance.vacation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class VacationRecordRes {
    private Long id;
    @Schema(description = "유급/무급", example = "1")
    private int isPaidLeave;
    @Schema(description = "날짜", example = "2023.03.03(월) - 2023.03.05(수)")
    private String date;
    @Schema(description = "일수", example = "2일")
    private String period;
    @Schema(description = "등록일", example = "2023-02-02")
    private String createdAt;
}
