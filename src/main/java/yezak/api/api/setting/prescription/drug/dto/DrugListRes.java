package yezak.api.api.setting.prescription.drug.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrugListRes {
    private Long id;
    private Long hospitalId;
    @Schema(description = "투여경로", example = "내복")
    private String dosageForm;

    @Schema(description = "사용자코드 id", example = "1")
    private Long customCodeId;
    @Schema(description = "사용자코드", example = "05000003")
    private String customCode;

    @Schema(description = "약품코드 id", example = "1")
    private Long drugCodeId;
    @Schema(description = "약품코드", example = "10010013")
    private String code;

    @Schema(description = "제품명", example = "필드시린지")
    private String koName;
    @Schema(description = "제약사", example = "(주)메디")
    private String companyName;
    @Schema(description = "단위", example = "정")
    private String unit;
    @Schema(description = "상한금액", example = "35250")
    private int maxValue;
    private int uninsuredPrice;
    @Schema(description = "등록주체", example = "라이브러리")
    private String inserted;
}
