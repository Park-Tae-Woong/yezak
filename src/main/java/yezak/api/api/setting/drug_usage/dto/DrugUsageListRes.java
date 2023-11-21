package yezak.api.api.setting.drug_usage.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrugUsageListRes {

    @Schema(description = "용법 구분 id", example = "1")
    private Long drugUsageTypeId;
    @Schema(description = "구분", example = "내복약")
    private String name;
    @Schema(description = "용법 id", example = "1")
    private Long usageId;
    @Schema(description = "사용자코드", example = "#1")
    private String customCode;
    @Schema(description = "용법내용", example = "1일1회")
    private String content;

}
