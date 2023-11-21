package yezak.api.api.admin.permission.role.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsertRoleReq {
    @Schema(description = "직군명", example = "병원장")
    private String koName;
    @Schema(description = "직급id", example = "1")
    private Long gradeId;
    @Schema(description = "직군카테고리id", example = "1")
    private Long roleCategoryId;
    @Schema(description = "병원id", example = "1")
    private Long hospitalId;
    @Hidden
    private Long id;
}
