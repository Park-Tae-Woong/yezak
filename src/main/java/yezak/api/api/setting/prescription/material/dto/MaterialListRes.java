package yezak.api.api.setting.prescription.material.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterialListRes {
    private Long id;
    @Schema(description = "재료코드", example = "A00130011")
    private String code;
    private String customCode;
    @Schema(description = "품명", example = "CARBON SET")
    private String name;
    @Schema(description = "규격", example = "1/0")
    private String specification;
    @Schema(description = "단위", example = "1EA")
    private String unit;
    @Schema(description = "상한가", example = "363401")
    private int maxValue;
    @Schema(description = "제조회사", example = "멀티플라이")
    private String manufacturer;
    @Schema(description = "재질", example = "Infection due")
    private String material;
    @Schema(description = "수입업소", example = "새한산업")
    private String importer;
    @Schema(description = "등록주체", example = "라이브러리")
    private String registrant;
    private Long hospitalId;
    private Integer uninsuredPrice;
}
