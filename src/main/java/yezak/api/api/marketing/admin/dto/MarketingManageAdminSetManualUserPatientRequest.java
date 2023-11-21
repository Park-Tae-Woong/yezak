package yezak.api.api.marketing.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketingManageAdminSetManualUserPatientRequest {
//    @Schema(description = "병원id", example = "1")
//    private Long hospitalId;
    @Schema(description = "담당자id", example = "192")
    private Long chargeId;
    @Schema(description = "선택한 row 정보")
    private List<MarketingManageAdminSetManualUserPatientRawInfo> rawInfoList;
}
