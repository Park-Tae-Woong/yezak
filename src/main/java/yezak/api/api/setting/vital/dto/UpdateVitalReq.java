package yezak.api.api.setting.vital.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class UpdateVitalReq {
    @Schema(description = "사용여부", example = "1")
    private int useYn;
    @Schema(description = "항목", example = "키")
    private String koName;
    @Schema(description = "단위", example = "cm")
    private String unit;
    @Schema(description = "최소값", example = "100")
    private String lowValue;
    @Schema(description = "최대값", example = "1000")
    private String maxValue;
    @Schema(description = "순서", example = "1")
    private int sort;
    @Schema(description = "바이탈id", example = "1")
    private Long id;
    @Hidden
    private Long hospitalId;
}
