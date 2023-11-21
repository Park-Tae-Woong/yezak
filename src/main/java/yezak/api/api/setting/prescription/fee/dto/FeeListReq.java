package yezak.api.api.setting.prescription.fee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeeListReq {
    @Schema(description = "분류id", example = "1")
    private Long categoryId;
    @Schema(description = "세부구분id", example = "1")
    private Long subdivisionId;
    @Schema(description = "검색어", example = "101")
    private String searchValue;
    @Schema(description = "병원id", example = "1", hidden = true)
    private Long hospitalId;
    private Integer offset;
    private Integer pageSize;
}
