package yezak.api.api.setting.prescription.material.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateCustomReq {
    @Schema(description = "재료코드", example = "A00130011")
    private String customCode;
    @Schema(description = "품명", example = "CARBON SET")
    private String name;
    @Schema(description = "규격", example = "1/0")
    private String specification;
    @Schema(description = "단위", example = "1EA")
    private String unit;
    @Schema(description = "비급여 금액", example = "13000")
    private int uninsuredPrice;
    @Schema(description = "제조회사", example = "멀티플라이")
    private String manufacturer;
    @Schema(description = "재질", example = "Infection due")
    private String material;
    @Schema(description = "수입업소", example = "새한산업")
    private String importer;
    @Schema(description = "재료 id", example = "id")
    private Long id;
    @Schema(description = "등록주체", example = "1")
    private Long hospitalId;
}
