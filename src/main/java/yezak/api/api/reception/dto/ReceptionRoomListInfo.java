package yezak.api.api.reception.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReceptionRoomListInfo {
    @Schema(description = "id")
    private Integer value;
    @Schema(description = "대기실 명")
    private String text;
}
