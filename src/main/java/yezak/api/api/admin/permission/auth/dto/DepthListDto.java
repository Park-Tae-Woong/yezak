package yezak.api.api.admin.permission.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepthListDto {

    @Schema(description = "상위 메뉴 id", example = "접수/수납")
    private Long id;
    @Schema(description = "병원 별 상위 메뉴", example = "수납/접수")
    private String koName;
    @Schema(description = "하위 메뉴")
    private List<DepthListDto2> depthListDto2;

}
