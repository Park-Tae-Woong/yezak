package yezak.api.api.admin.hospital.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OvertimeReq {
    @Schema(description = "사용여부", example = "1")
    private int useYn;
    @Schema(description = "초기화시 시작값", example = "09:00")
    private String defaultStart;
    @Schema(description = "초기화시 종료값", example = "18:00")
    private String defaultEnd;
    @Hidden
    @Schema(description = "병원id", example = "1")
    private Long hospitalId;
    @Schema(description = "가산시간 id", example = "1")
    private Long id;
}
