package yezak.api.api.setting.prescription.material.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateMaterial {
    private Long id;
    @Schema(description = "코드", example = "AAA123")
    private String customCode;
    @Schema(description = "비급여금액", example = "10000")
    private Integer uninsuredPrice;
    @Schema(description = "품명", example = "CARBON SET")
    private String name;
    @Schema(description = "규격", example = "1/0")
    private String specification;
    @Schema(description = "단위", example = "1EA")
    private String unit;
    @Schema(description = "제조회사", example = "멀티플라이")
    private String manufacturer;
    @Schema(description = "재질", example = "Infection due")
    private String material;
    @Schema(description = "수입업소", example = "새한산업")
    private String importer;
    @Schema(description = "재료대코드 id", example = "1", hidden = true)
    private Long materialCodeId;
    @Schema(description = "적용일자", example = "2023-07-19")
    private String applicatedDate;

    @Hidden
    private Long hospitalId;
}
