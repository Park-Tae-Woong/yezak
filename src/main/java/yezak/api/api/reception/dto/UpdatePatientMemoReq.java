package yezak.api.api.reception.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UpdatePatientMemoReq {
    @Schema(description = "내용", example = "잘생김 주의")
    private String content;
    @Hidden
    @Schema(description = "내id", example = "1")
    private Long userId;
    @Schema(description = "메모id", example = "1")
    private Long memoId;
}
