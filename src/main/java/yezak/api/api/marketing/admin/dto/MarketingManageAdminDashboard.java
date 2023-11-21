package yezak.api.api.marketing.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketingManageAdminDashboard {
    @Schema(description = "대시보드 - 총DB수, 내원예약수, 예약금")
    private MarketingManageAdminDashboardDbCount dbCount;
    @Schema(description = "대시보드 - 성별/연령")
    private MarketingManageAdminDashboardGenderAgeCount genderAgeCount;
    @Schema(description = "대시보드 - DB 유입경로")
    private List<MarketingManageAdminDashboardAccessRootCount> accessRootCount;
}
