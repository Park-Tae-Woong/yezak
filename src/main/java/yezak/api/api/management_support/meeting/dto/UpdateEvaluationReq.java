package yezak.api.api.management_support.meeting.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateEvaluationReq {
    @Schema(description = "관리자평가", example = "A")
    private String evaluation;
    @Schema(description = "결정사항 id", example = "1")
    private Long conclusionId;
}
