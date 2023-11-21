package yezak.api.api.schedule.counseling.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "상담실 리스트(셀렉트박스용)")
public class RoomListResponse {
    @Schema(description = "상담실 id")
    private Long id;

    @Schema(description = "상담실 이름")
    private String koName;
}
