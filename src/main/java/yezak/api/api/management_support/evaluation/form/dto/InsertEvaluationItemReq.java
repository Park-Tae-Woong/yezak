package yezak.api.api.management_support.evaluation.form.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsertEvaluationItemReq {
    @Schema(description = "항목명", example = "퇴근시간을 잘 준수하는가?")
    private String itemName;

    @Schema(description = "항목별 총점", example = "20")
    private int maxValue;

    @Schema(description = "평가지 id", example = "1", hidden = true)
    private Long evaluationFormId;
}
