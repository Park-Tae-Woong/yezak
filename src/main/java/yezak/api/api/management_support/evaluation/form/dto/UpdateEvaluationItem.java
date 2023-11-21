package yezak.api.api.management_support.evaluation.form.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateEvaluationItem {
    @Schema(description = "평가지 id", example = "1")
    private Long evaluationFormId;
    @Schema(description = "항목명", example = "연차는 잘 소진하는가?")
    private String name;
    @Schema(description = "만점", example = "10")
    private int maxValue;
    @Schema(description = "평가항목 id", example = "1")
    private Long id;
}
