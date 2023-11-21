package yezak.api.api.marketing.admin.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketingManageAdminDbListResponse {
    private List<MarketingManageAdminDbData> dbData;
    private Integer totalDbDataCount;
}
