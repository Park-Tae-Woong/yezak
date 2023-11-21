package yezak.api.api.setting.prescription.fee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeeDetailRes {
    @Schema(description = "사용자코드")
    private String customCode;
    @Schema(description = "분류id")
    private Long categoryId;
    @Schema(description = "분류")
    private String categoryName;
    @Schema(description = "세부구분id")
    private Long subdivisionId;
    @Schema(description = "세부구분")
    private String subdivisionName;
    @Schema(description = "청구코드")
    private String code;
    @Schema(description = "한글명")
    private String koName;
    @Schema(description = "영문명")
    private String enName;
    @Schema(description = "보험가")
    private int clinicPrice;
    @Schema(description = "본인부담률id")
    private String payTypeId;
    @Schema(description = "본인부담률")
    private String payTypeName;
    @Schema(description = "적용일")
    private LocalDate applicatedDate;
    @Schema(description = "등록주체")
    private Long hospitalId;
}
