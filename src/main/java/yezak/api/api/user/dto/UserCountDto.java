package yezak.api.api.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCountDto {
    @Schema(description = "총 수", example = "30")
    private int totalUser;
    @Schema(description = "재직 수", example = "15")
    private int employee;
    @Schema(description = "퇴사 수", example = "15")
    private int exEmployee;
}
