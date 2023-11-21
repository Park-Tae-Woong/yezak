package yezak.api.api.marketing.normal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketingManageNormalDbListResponse {
    @Schema(description = "db list")
    private List<MarketingManageNormalDbData> dbData;
    @Schema(description = "총건수", example = "12")
    private Integer totalDbDataCount;
}
