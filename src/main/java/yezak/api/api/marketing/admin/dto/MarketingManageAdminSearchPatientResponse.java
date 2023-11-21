package yezak.api.api.marketing.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketingManageAdminSearchPatientResponse {
    @Schema(description = "DB 정보")
    private MarketingManageAdminSearchPatientInfo patientInfo;
    @Schema(description = "상담이력")
    private List<MarketingManageAdminCounselHistoryInfo> counselHistoryInfo;
    @Schema(description = "예약정보")
    private List<MarketingManageReservationInfo> reservationInfoList;
}
