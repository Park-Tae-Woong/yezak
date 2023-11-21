package yezak.api.api.management_support.evaluation.form.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationItemList {
    @Schema(description = "No", example = "1")
    private int no;

    @Schema(description = "인사평가항목", example = "출퇴근 시간을 잘 준수하는가?")
    private String name;

    @Schema(description = "사용자코드", example = "20")
    private int maxValue;
}
