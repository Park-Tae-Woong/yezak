package yezak.api.api.marketing.admin.dto;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketingManageAdminRequest {
    private Long hospitalId;
    private String startDate;
    private String endDate;
    private Long userId;
    private Long counselingStatusId;
    private String name;
}
