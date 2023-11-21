package yezak.api.api.admin.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SystemBackgroundImageInfoResponse {
    @Schema(description = "사용배경 상태(0:default / 1:custom)", example = "0")
    private Integer customWallpaperState;
    @Schema(description = "파일경로", example = "/yezak/file/image/default-background.jpg")
    private String fullPath;
}
