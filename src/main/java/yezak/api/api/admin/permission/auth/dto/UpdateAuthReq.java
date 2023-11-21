package yezak.api.api.admin.permission.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateAuthReq {
    @Schema(description = "직군id", example = "1")
    private Long roleId;
    @Schema(description = "접근 가능 여부", example = "1")
    private int access;

}
