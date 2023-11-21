package yezak.api.api.admin.hospital.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OvertimeRes {
    private Long id;
    @Schema(description = "사용여부", example = "1")
    private int useYn;
    @Schema(description = "가산시간타입", example = "주간수가")
    private String name;
    @Schema(description = "초기화시 시작값", example = "09:00")
    private String defaultStart;
    @Schema(description = "초기화시 종료값", example = "18:00")
    private String defaultEnd;
}
