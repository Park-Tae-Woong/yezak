package yezak.api.api.schedule.counseling.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "바이탈 과거이력")
public class VitalHistoryResponse {
    @Schema(description = "Vital Sign Masters Id")
    private Long vitalId;

    @Schema(description = "측정값")
    private String value;

    @JsonFormat(pattern="yyyy.MM.dd")
    @Schema(description = "생성일자")
    private String createdAt;
}
