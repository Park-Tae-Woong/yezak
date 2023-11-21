package yezak.api.api.admin.permission.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class SalaryUpdateReq {
    @Schema(description = "급여형태", example = "test@test.com")
    private Long salaryTypeId;
    @Schema(description = "계약 시작날짜", example = "2022-06-09")
    private LocalDate start;
    @Schema(description = "계약 종료날짜", example = "2022-06-10")
    private LocalDate end;
    @Schema(description = "금액", example = "30000000")
    private Long amount;
    @Schema(description = "유저 id", example = "1")
    private Long userId;
}
