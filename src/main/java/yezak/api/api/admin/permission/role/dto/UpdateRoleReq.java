package yezak.api.api.admin.permission.role.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class UpdateRoleReq {
    @Schema(description = "직군명", example = "전문의")
    private String koName;
    @Schema(description = "직급id", example = "1")
    private Long gradeId;
    @Schema(description = "직군id", example = "11")
    private Long id;
    @Schema(description = "직군카테고리id", example = "11")
    private Long roleCategoryId;
}
