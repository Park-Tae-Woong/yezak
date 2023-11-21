package yezak.api.api.setting.prescription.drug.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaxValueChangeLogRes {
    @Schema(description = "적용날짜", example = "2001-01-01")
    private String applicatedDate;
    @Schema(description = "금액", example = "15230")
    private Integer maxValue;
}
