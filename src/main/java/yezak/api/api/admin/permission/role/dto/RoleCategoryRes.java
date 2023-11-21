package yezak.api.api.admin.permission.role.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleCategoryRes {
    @Schema(description = "직군카테고리명", example = "의사")
    private String koName;
    @Schema(description = "직군카테고리id", example = "1")
    private Long id;
    @Schema(description = "직군 수", example = "1")
    private int count;
}
