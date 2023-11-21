package yezak.api.api.setting.prescription.material.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsertMaterialOption {
    @Schema(description = "재료코드", example = "A00130011")
    private String customCode;
    @Schema(description = "비급여 금액", example = "13000")
    private int uninsuredPrice;
    @Schema(description = "재료대코드 id", example = "1", hidden = true)
    private Long materialCodeId;
}
