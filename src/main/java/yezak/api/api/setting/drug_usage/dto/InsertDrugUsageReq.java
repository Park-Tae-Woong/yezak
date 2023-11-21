package yezak.api.api.setting.drug_usage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsertDrugUsageReq {
    @Schema(description = "사용자코드", example = "#1")
    private String customCode;
    @Schema(description = "구분", example = "1")
    private Long drugUsageTypeId;
    @Schema(description = "용법내용", example = "1일3회")
    private String content;
    @Schema(description = "병원id", example = "1", hidden = true)
    private Long hospitalId;
}
