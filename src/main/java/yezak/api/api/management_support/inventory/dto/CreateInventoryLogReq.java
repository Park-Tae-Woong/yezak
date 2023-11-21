package yezak.api.api.management_support.inventory.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateInventoryLogReq {
    @Schema(description = "반영일자", example = "2023-08-16")
    private String controlledAt;
    @Schema(description = "조정 구분", example = "1")
    private Long controlTypeId;

    @Hidden
    private Integer beforeAmount;
    @Schema(description = "조정 수량", example = "5")
    private Integer controlledAmount;

    @Hidden
    private Integer afterAmount;
    @Schema(description = "비고", example = "고비")
    private String remark;
    @Schema(description = "재고id", example = "1")
    private Long hospitalInventoryId;

    @Hidden
    private Long userId;
}
