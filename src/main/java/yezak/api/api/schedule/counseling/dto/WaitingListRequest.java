package yezak.api.api.schedule.counseling.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(description = "대기리스트 조회용")
public class WaitingListRequest {
    @Schema(hidden = true, description = "방 id")
    private Long roomId;

    @Schema(description = "담당자 role_category_id")
    private Long roleId;
}
