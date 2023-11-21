package yezak.api.api.marketing.dashboard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdExpenseProfitInfo {
    @Schema(description = "광고비", example = "10000000")
    private Integer adExpense;
    @Schema(description = "매출액", example = "10000000")
    private Integer profit;
    @Schema(description = "ROAS", example = "1")
    private double roas;
    @Schema(description = "일자", example = "2023-07")
    private String date;
}
