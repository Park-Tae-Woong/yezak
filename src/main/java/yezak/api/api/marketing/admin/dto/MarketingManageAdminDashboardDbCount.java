package yezak.api.api.marketing.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketingManageAdminDashboardDbCount {
    @Schema(description = "총DB수", example = "80")
    private Integer totalDBCount;
    @Schema(description = "내원예약수", example = "80")
    private Integer reservationCount;
    @Schema(description = "예약금", example = "4000000")
    private Long deposit;
}
