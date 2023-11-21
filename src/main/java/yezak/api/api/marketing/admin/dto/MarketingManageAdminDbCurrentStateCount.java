package yezak.api.api.marketing.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketingManageAdminDbCurrentStateCount {
    @Schema(description = "총 DB 수", example = "192")
    private Integer totalCount;
    @Schema(description = "할당된 DB 수", example = "161")
    private Integer chargeCount;
    @Schema(description = "미담당 DB 수", example = "31")
    private Integer notChargeCount;
}
