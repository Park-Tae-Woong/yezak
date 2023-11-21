package yezak.api.api.admin.hospital.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfficeHourReq {
    @Schema(description = "시작시간", example = "09:00")
    private String start;
    @Schema(description = "종료시간", example = "18:00")
    private String end;
    @Schema(description = "사용여부(Y:1 / N:0)", example = "1")
    private int useYn;
    @Schema(description = "업무시간 id", example = "1")
    private Long id;
    @Hidden
    @Schema(description = "병원 id", example = "1")
    private Long hospitalId;
}
