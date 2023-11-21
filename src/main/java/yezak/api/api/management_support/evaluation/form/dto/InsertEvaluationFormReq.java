package yezak.api.api.management_support.evaluation.form.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsertEvaluationFormReq {
    @Schema(description = "평가지 id", example = "1", hidden = true)
    private Long id;

    @Schema(description = "평가지 이름", example = "test")
    private String name;

    @Schema(description = "병원id", example = "1")
    private Long hospitalId;
}
