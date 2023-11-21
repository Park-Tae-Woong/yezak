package yezak.api.api.management_support.inventory.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryListReq {
    @Schema(description = "검색어", example = "약")
    private String searchValue;
    @Hidden
    private Long hospitalId;
    @Schema(description = "정렬 타입", example = "asc or desc")
    private String sortType;
    private Integer offset;
    private Integer pageSize;
}
