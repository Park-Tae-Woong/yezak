package yezak.api.domain.schedule.counseling;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "활력징후값")
public class PatientVitalSignsTbl {
    @Schema(description = "활력 징후값 id")
    private Long id;

    @Schema(description = "활력 징후 마스터 id")
    private Long vitalSignMasterName;

    @Schema(description = "진료 기록 id")
    private Long medicalRecordId;

    @Schema(description = "측정값")
    private String value;

    @Schema(description = "생성일자")
    private Timestamp createdAt;

    @Schema(description = "수정일자")
    private Timestamp updatedAt;
}
