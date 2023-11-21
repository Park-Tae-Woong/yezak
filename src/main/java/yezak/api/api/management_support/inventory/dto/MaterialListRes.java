package yezak.api.api.management_support.inventory.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterialListRes {
    @Schema(description = "재료대id", example = "1")
    private Long id;
    @Schema(description = "재료대코드", example = "ACSDRE12")
    private String code;
    @Schema(description = "품명", example = "주사기")
    private String name;
    @Schema(description = "규격", example = "전규격")
    private String specification;
    @Schema(description = "단위", example = "mm")
    private String unit;
    @Schema(description = "제조회사", example = "멀티플라이")
    private String manufacturer;
    @Schema(description = "재질", example = "아다만티움")
    private String material;
    @Schema(description = "수입업소", example = "LG")
    private String importer;
}