package yezak.api.api.setting.drug_usage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateDrugUsageReq {
    @Schema(description = "사용자코드", example = "#1")
    private String customCode;

    @Schema(description = "용법구분", example = "2")
    private Long drugUsageTypeId;

    @Schema(description = "내용", example = "1일2회")
    private String content;

    @Schema(description = "용법id", example = "1")
    private Long id;

}
