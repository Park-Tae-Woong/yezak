package yezak.api.api.schedule.counseling.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(description = "바이탈 정보")
public class PatientVitalRequest {
    @Schema(description = "활력 징후 마스터 id")
    private Long vitalSignMasterName;

    @Schema(description = "측정값")
    private String value;
}
