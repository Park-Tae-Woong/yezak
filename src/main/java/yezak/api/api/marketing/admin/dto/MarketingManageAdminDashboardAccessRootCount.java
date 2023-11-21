package yezak.api.api.marketing.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketingManageAdminDashboardAccessRootCount {
    @Schema(description = "유입경로id", example = "1")
    private Integer accessRootId;
    @Schema(description = "유입경로명", example = "네이버 블로그 1번")
    private String name;
    @Schema(description = "count", example = "1")
    private Integer count;
}
