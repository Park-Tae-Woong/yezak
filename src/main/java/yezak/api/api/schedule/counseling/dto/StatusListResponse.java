package yezak.api.api.schedule.counseling.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "상태값 리스트(셀렉트박스용)")
public class StatusListResponse {
    @Schema(description = "상태값 id")
    private Long id;

    @Schema(description = "상태값 이름")
    private String name;
}
