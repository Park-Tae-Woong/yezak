package yezak.api.api.setting.prescription.drug.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsertDrugReq {
    @Schema(description = "자동 생성 id", example = "1", hidden = true)
    private Long id;

    @Schema(description = "id값 자동 입력", example = "1", hidden = true)
    private Long drugCodeId;

    @Schema(description = "사용자코드", example = "11231")
    private String customCode;
    @Schema(description = "투약경로", example = "내복")
    private String dosageForm;

    @Schema(description = "약품코드", example = "131313")
    private String code;

    @Hidden
    @Schema(description = "분업구분", example = "1")
    private Long hospitalId;

    @Schema(description = "제품명", example = "타이레놀")
    private String koName;

    @Schema(description = "제약사 명칭", example = "예작")
    private String companyName;

    @Schema(description = "규격", example = "test")
    private String capacity;

    @Schema(description = "단위", example = "정")
    private String unit;

    @Schema(description = "비급여금액", example = "10000")
    private int uninsuredPrice;
    @Schema(description = "적용날짜", example = "2023-02-02")
    private String applicatedDate;

}
