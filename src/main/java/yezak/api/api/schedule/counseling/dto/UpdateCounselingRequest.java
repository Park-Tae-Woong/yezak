package yezak.api.api.schedule.counseling.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(description = "상담 내용 업데이트")
public class UpdateCounselingRequest {
    @Schema(description = "상담 id", hidden = true)
    private Long id;

    @Schema(description = "상담 내용")
    private String content;
}
