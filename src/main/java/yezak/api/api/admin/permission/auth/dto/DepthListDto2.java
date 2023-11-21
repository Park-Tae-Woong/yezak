package yezak.api.api.admin.permission.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepthListDto2 {
    private Long id;
    @Schema(description = "하위 메뉴", example = "접수수납")
    private String d2KoName;
    private List<DepthListDto3> depthListDto3;
}
