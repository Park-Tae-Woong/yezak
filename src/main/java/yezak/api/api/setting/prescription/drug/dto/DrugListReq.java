package yezak.api.api.setting.prescription.drug.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrugListReq {
    @Schema(description = "투여경로", example = "내복")
    private String dosageForm;
    @Schema(description = "검색어", example = "사용자코드, 제품명, 제약사 검색")
    private String searchValue;
    @Hidden
    private Long hospitalId;
    @Schema(description = "시작점", example = "1")
    private Integer offset;
    @Schema(description = "페이지 크기", example = "15")
    private Integer pageSize;
}
