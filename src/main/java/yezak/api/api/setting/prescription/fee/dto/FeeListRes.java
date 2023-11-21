package yezak.api.api.setting.prescription.fee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeeListRes {
    @Schema(description = "수가 id", example = "1")
    private Long id;
    @Schema(description = "분류id", example = "1")
    private Long categoryId;
    @Schema(description = "분류", example = "처치 및 수수료")
    private String categoryName;
    @Schema(description = "세부구분id", example = "1")
    private Long subdivisionId;
    @Schema(description = "세부구분", example = "피부 및 연부조직")
    private String subdivisionName;
    @Schema(description = "사용자코드", example = "0500008")
    private String customCode;
    @Schema(description = "청구코드", example = "10010017")
    private String code;
    @Schema(description = "한글명", example = "간동맥결찰술")
    private String koName;
    @Schema(description = "영문명", example = "Hepatic Artery Ligation")
    private String enName;
    @Schema(description = "보험가", example = "1458000")
    private Integer clinicPrice;
    @Schema(description = "비급여", example = "14580000")
    private Integer uninsuredPrice;
    @Schema(description = "등록주체", example = "라이브러리")
    private String inserted;

}
