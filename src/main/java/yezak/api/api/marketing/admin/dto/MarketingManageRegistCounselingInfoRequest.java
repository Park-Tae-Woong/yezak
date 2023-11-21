package yezak.api.api.marketing.admin.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketingManageRegistCounselingInfoRequest {
    @Schema(description = "환자id", example = "1")
    private Integer patientsId;
    @Hidden
    @Schema(description = "담당자id", example = "1")
    private Long userId;
    @Schema(description = "상담 시/수술", example = "시술1,시술2,시술3")
    private String askedProductStr;
    @Schema(description = "상담내용", example = "상담내용")
    private String counselContent;
}
