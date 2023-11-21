package yezak.api.api.marketing.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketingManageCounselingStatusesResponse {
    @Schema(description = "상담상태id(1미연락 2상담진행중 3상담완료 4연락불가)", example = "3")
    private Long value;//id
    @Schema(description = "상담상태명", example = "상담완료")
    private String text;//name
}
