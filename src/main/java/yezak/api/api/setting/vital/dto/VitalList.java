package yezak.api.api.setting.vital.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class VitalList {
    @Schema(description = "id", example = "1")
    private Long id;
    @Schema(description = "사용여부", example = "1")
    private int useYn;
    @Schema(description = "항목", example = "호흡수")
    private String koName;
    @Schema(description = "단위", example = "회/min")
    private String unit;
    @Schema(description = "최소값", example = "100")
    private String lowValue;
    @Schema(description = "최대값", example = "1000")
    private String maxValue;
    @Schema(description = "순서", example = "1")
    private int sort;
    @Schema(description = "고정", example = "1")
    private int isFixed;
}
