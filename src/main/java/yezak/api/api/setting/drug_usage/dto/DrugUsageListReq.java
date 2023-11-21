package yezak.api.api.setting.drug_usage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrugUsageListReq {
    @Schema(description = "용법구분", example = "1")
    private Long drugUsageTypeId;
    @Schema(description = "검색어", example = "사용자코드, 용법내용")
    private String searchValue;
    private Long hospitalId;
    private int offset;
    private int pageSize;
}
