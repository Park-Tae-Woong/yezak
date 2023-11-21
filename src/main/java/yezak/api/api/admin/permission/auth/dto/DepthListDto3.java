package yezak.api.api.admin.permission.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepthListDto3 {
    private Long id;
    @Schema(description = "상세 메뉴 노출 여부", example = "접수수납")
    private String d3KoName;
    @Schema(description = "관리자용 3뎁스 여부", example = "1")
    private int isForAdmin;

}
