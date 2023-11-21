package yezak.api.api.management_support.meeting.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateConclusionContentReq {
    @Schema(description = "결정사항 id", example = "1")
    private Long conclusionId;
    @Schema(description = "상세내용", example = "test")
    private String detail;

}
