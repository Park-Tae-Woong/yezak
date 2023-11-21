package yezak.api.api.admin.permission.role.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetRolesRes {
    @Schema(description = "직군명", example = "의사")
    private String koName;
    @Schema(description = "권한", example = "일반")
    private String name;
    @Schema(description = "직군 id", example = "1")
    private Long roleId;
    @Schema(description = "직급 id", example = "1")
    private Long gradeId;
    @Schema(description = "계정 수", example = "1")
    private int count;
}
