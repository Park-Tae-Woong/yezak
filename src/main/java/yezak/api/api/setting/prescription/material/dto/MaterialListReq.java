package yezak.api.api.setting.prescription.material.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterialListReq {
    @Schema(description = "병원 id", example = "1", hidden = true)
    private Long hospitalId;
    @Schema(description = "검색어", example = "사용자코드, 한글명칭")
    private String searchValue;
    private Integer offset;
    private Integer pageSize;
}
