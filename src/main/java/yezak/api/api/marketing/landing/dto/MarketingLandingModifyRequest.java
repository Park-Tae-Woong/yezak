package yezak.api.api.marketing.landing.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketingLandingModifyRequest {
    @Schema(description = "id", example = "1")
    private Integer id;
    @Schema(description = "유입경로 명", example = "1네이버 블로그 1번광고")
    private String koName;
    @Schema(description = "url", example = "https://www.meditwin12.com/ed/1")
    private String landingPageUrl;
    @Schema(description = "사용여부(Y:1 / N:0)", example = "1")
    private Integer useYn;
    @Schema(description = "비고", example = "키워드: 1수술")
    private String remark;
    @Hidden
    @Schema(description = "병원id", example = "1")
    private Long hospitalId;
}
