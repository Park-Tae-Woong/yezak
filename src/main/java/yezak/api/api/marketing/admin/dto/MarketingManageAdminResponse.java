package yezak.api.api.marketing.admin.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketingManageAdminResponse {
    private MarketingManageAdminDashboard adminDashboard;
    private MarketingManageAdminDbCurrentStateCount dbCurrentStateCount;
    private List<MarketingManageAdminDbChargeCurrentStateCount> dbChargeCurrentStateCount;
    private List<MarketingManageAdminDbData> dbData;
    private Integer totalDbDataCount;
}
