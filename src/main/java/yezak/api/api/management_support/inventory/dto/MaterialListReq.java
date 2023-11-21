package yezak.api.api.management_support.inventory.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterialListReq {
    @Schema(description = "검색어", example = "1")
    private String searchValue;
    @Hidden
    private Long hospitalId;
    private Integer offset;
    private Integer pageSize;
}
