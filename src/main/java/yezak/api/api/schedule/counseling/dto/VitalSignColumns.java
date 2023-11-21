package yezak.api.api.schedule.counseling.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "바이탈 정보 컬럼")
public class VitalSignColumns {
    @Schema(description = "Vital Sign Masters Id")
    private Long id;

    @Schema(description = "항목")
    private String koName;

    @Schema(description = "표출 순서")
    private int sort;

    @Schema(description = "단위")
    private String unit;
}
