package yezak.api.api.management_support.inventory.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateAmountReq {
    @Schema(description = "수량", example = "200")
    private Integer amount;
    private Long id;
}
