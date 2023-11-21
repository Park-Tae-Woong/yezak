package yezak.api.api.admin.permission.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllDepthList {
    @Schema(description = "뎁스리스트")
    private List<DepthListDto> depthListDto;

    @Schema(description = "상위노출여부(0 : 미노출, 1 : 노출)", example = "1")
    private List<Long> isChecked;
}
