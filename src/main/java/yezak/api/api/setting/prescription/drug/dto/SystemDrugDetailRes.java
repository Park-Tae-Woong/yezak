package yezak.api.api.setting.prescription.drug.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SystemDrugDetailRes {
    @Schema(description = "약품 id", example = "1")
    private Long id;
    @Schema(description = "병원id", example = "1")
    private Long hospitalId;

    @Schema(description = "사용자코드", example = "05050500")
    private String customCode;

    private String dosageForm;
    @Schema(description = "약품코드", example = "501052000")
    private String drugCode;

    @Schema(description = "제품명", example = "리브감마에스")
    private String koName;

    @Schema(description = "제약사 명칭", example = "(주)크리스탈")
    private String companyName;

    @Schema(description = "규격", example = "test")
    private String capacity;

    @Schema(description = "단위", example = "mL/병")
    private String unit;

    @Schema(description = "상한금액", example = "180400")
    private int maxValue;

    @Schema(description = "본인부담률 id", example = "30")
    private String payTypeId;

    @Schema(description = "본인부담률 이름", example = "30")
    private String payTypeName;

    @Schema(description = "약가적용일자", example = "2022-02-02")
    private String applicatedDate;


}
