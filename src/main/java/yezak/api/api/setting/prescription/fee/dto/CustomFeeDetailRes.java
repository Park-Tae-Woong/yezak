package yezak.api.api.setting.prescription.fee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomFeeDetailRes {
    @Schema(description = "사용자코드")
    private String customCode;
    @Schema(description = "등록주체")
    private Long hospitalId;
    @Schema(description = "분류id")
    private Long categoryId;
    @Schema(description = "분류")
    private String categoryName;
    @Schema(description = "세부구분id")
    private Long subdivisionId;
    @Schema(description = "세부구분")
    private String subdivisionName;
    @Schema(description = "한글명")
    private String koName;
    @Schema(description = "영문명")
    private String enName;
    @Schema(description = "비급여 금액")
    private int uninsuredPrice;
    @Schema(description = "적용일")
    private LocalDate applicatedDate;
}
