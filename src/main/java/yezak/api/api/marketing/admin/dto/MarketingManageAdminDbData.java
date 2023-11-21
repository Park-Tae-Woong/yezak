package yezak.api.api.marketing.admin.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketingManageAdminDbData {
    private Integer id;
    private String name;
    private String phoneNumber;
    private String availableTime;
    private String accessRootName;
    private LocalDate createdAt;
    private Long chargeId;
    private String chargeName;
    private String counselingStatus;
    private String reservationStatus;
}
