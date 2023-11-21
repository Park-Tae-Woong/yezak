package yezak.api.api.management_support.inventory.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UseMaterialReq {
    @Schema(description = "재료대id", example = "1")
    private Long materialId;
    @Hidden
    private Long hospitalId;
}
