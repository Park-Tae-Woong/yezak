package yezak.api.api.management_support.inventory.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryLogListReq {
    @Schema(description = "검색어", example = "약")
    private String searchValue;
    @Schema(description = "시작날짜", example = "2023-01-01")
    private String start;
    @Schema(description = "끝 날짜", example = "2023-02-02")
    private String end;
    @Schema(description = "조정구분", example = "1이면 입고, 2면 사용")
    private Long controlTypeId;
    @Hidden
    private Long hospitalId;
    private int offset;
    private int pageSize;
}
