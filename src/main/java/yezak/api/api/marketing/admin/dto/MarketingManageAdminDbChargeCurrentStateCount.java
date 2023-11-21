package yezak.api.api.marketing.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketingManageAdminDbChargeCurrentStateCount {
    @Schema(description = "담당자명", example = "김상담")
    private String name;
    @Schema(description = "DB수", example = "35")
    private Integer count;
}
