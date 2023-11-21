package yezak.api.api.management_support.meeting.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateContentReq {
    @Schema(description = "회의 id", example = "1")
    private Long meetingId;
    @Schema(description = "내용", example = "test")
    private String meetingContent;
}
