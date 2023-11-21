package yezak.api.api.admin.system.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SystemManageBackgroundStateChangeRequest {
    @Schema(description = "사용배경 상태(0:default / 1:custom)", example = "0")
    private Integer customWallpaperState;

    @Hidden
    @Schema(description = "병원id", example = "1")
    private Long hospitalId;
}
