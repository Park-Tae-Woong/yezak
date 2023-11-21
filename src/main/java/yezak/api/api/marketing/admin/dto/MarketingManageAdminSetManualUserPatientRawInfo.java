package yezak.api.api.marketing.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketingManageAdminSetManualUserPatientRawInfo {
    @Schema(description = "id", example = "1")
    private Long id;
    @Schema(description = "담당자id", example = "1")
    private Long chargeId;
}
