package yezak.api.api.marketing.guest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketingManageGuestRegistResponse {
    @Schema(description = "등록 수", example = "50")
    private Integer resultCount;
    @Schema(description = "중복 수", example = "2")
    private Integer overlapCount;
}
