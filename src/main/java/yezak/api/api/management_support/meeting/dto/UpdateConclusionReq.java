package yezak.api.api.management_support.meeting.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateConclusionReq {
    @Schema(description = "결정사항 id", example = "1")
    private Long conclusionId;
    @Schema(description = "할일", example = "워크샵 기획")
    private String conclusionContent;
    @Schema(description = "마감일자", example = "2022-12-20")
    private String due;
//    @Schema(description = "마감시간", example = "15:00")
//    private String dueTime;
    @Schema(description = "담당자id", example = "1")
    private Long managerId;

}
