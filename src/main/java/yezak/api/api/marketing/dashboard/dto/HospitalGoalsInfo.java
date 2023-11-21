package yezak.api.api.marketing.dashboard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HospitalGoalsInfo {
    @Schema(description = "금월 매출 목표액", example = "10000000")
    private Integer monthSalesTarget;
    @Schema(description = "전월 매출액", example = "10000000")
    private Integer preMonthSales;
    @Schema(description = "금월 매출액", example = "10000000")
    private Integer monthSales;
}
