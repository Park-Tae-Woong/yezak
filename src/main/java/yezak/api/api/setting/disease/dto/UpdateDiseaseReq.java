package yezak.api.api.setting.disease.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateDiseaseReq {
    @Schema(description = "사용자코드", example = "aa00")
    private String customCode;
    @Schema(description = "상병 id", example = "1")
    private Long diseaseId;
    @Hidden
    private Long hospitalId;
}
