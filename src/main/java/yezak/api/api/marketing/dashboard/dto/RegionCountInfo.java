package yezak.api.api.marketing.dashboard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RegionCountInfo {
    @Schema(description = "지역 명", example = "서울특별시 마포구")
    private String regionName;
    @Schema(description = "count", example = "1")
    private Integer count;
    @Schema(description = "total count", example = "10")
    private Integer totalCount;
}
