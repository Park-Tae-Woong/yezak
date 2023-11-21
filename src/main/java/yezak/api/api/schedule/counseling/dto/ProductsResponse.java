package yezak.api.api.schedule.counseling.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "병원 상품 리스트")
public class ProductsResponse {
    @Schema(description = "사용자 코드")
    private String customCode;

    @Schema(description = "시수술 상품명")
    private String product;

    @Schema(description = "가격")
    private int price;
}
