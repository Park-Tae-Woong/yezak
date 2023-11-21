package yezak.api.api.marketing.normal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketingManageRoomInfo {
    @Schema(description = "대기실id", example = "1")
    private Long id;
    @Schema(description = "대기실명", example = "대기실1")
    private String name;
}
