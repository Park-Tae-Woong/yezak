package yezak.api.api.marketing.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketingManageAdminSetAutoUserPatientRequest {
    @Schema(description = "자동분배 배분 수", example = "3")
    private Integer divisionCount;
//    @Schema(description = "자동분배 대상 id들(담당자 지정이 안된id)", example = "1,2,3,4")
//    private String patientIdStr;
//    @Schema(description = "병원id", example = "1")
//    private Long hospitalId;
}
