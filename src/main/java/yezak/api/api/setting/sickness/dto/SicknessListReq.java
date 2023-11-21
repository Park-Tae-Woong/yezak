package yezak.api.api.setting.sickness.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SicknessListReq {
    @Schema(description = "병원id", example = "1")
    private Long hospitalId;
    @Schema(description = "검색어", example = "1Ag")
    private String searchValue;
}
