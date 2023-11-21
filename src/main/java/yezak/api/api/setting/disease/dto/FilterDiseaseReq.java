package yezak.api.api.setting.disease.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilterDiseaseReq {
    @Schema(description = "완전 구분", example = "완전상병")
    private String perfectCodeChecker;
    @Schema(description = "사용자코드, 한글명칭 검색", example = "콜레라")
    private String searchValue;
    @Hidden
    private Long hospitalId;
    private int offset;
    private int pageSize;
}
