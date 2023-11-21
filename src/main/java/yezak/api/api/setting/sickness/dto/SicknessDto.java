package yezak.api.api.setting.sickness.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SicknessDto {
    @Schema(description = "id", example = "1")
    private Long id;
    @Schema(description = "사용자코드", example = "1Ag")
    private String customCode;
    @Schema(description = "증상내용", example = "HbsAb(+)1")
    private String content;
}
