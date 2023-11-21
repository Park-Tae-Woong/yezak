package yezak.api.api.admin.room.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class ScheduleCategoriesInfo {
    @Schema(description = "id", example = "1")
    private Integer id;
    @Schema(description = "상위 카테고리 명", example = "대면상담실")
    private String koName;
}
