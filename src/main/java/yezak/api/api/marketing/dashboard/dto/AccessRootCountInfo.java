package yezak.api.api.marketing.dashboard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccessRootCountInfo {
    @Schema(description = "유입경로 명", example = "네이버 블로그 1번 광고")
    private String name;
    @Schema(description = "유입경로 count", example = "1")
    private Integer count;
}
