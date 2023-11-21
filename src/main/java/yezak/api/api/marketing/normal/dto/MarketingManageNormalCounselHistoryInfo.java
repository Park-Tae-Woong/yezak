package yezak.api.api.marketing.normal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketingManageNormalCounselHistoryInfo {
    @Schema(description = "상담id", example = "1")
    private Long counselingId;
    @Schema(description = "고객id", example = "1")
    private Long patientId;
    @Schema(description = "상담 시/수술", example = "심박기 거치술 / 혈관성형술(팻취이용한경우)")
    private String askedProduct;
    @Schema(description = "상담노트", example = "상담내용")
    private String content;
    @Schema(description = "담당자명", example = "정실장")
    private String userName;
    @Schema(description = "직군명", example = "전화상담사")
    private String roleName;
    @Schema(description = "상담일시", example = "2023-05-01 13:30:00")
    private String createDate;
}
