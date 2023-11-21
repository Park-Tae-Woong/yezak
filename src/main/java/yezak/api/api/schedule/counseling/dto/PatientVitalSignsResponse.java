package yezak.api.api.schedule.counseling.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "환자별 바이탈 정보")
public class PatientVitalSignsResponse {
    @Schema(description = "환자별 바이탈 id")
    private Long id;

    @Schema(description = "활력 징후값 id")
    private Long vitalSignMasterName;

    @Schema(description = "측정값")
    private String value;

    @JsonFormat(pattern="yyyy.MM.dd")
    @Schema(description = "생성일자")
    private String createdAt;
}
