package yezak.api.api.marketing.admin.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketingManageRegistReservationInfoRequest {
    @Hidden
    @Schema(description = "예약id", example = "1")
    private Long id;
    @Schema(description = "고객id", example = "1")
    private Long patientId;
    @Schema(description = "대기실id", example = "1")
    private Long roomId;
    @Schema(description = "예약일시", example = "2023-05-01 13:30:00")
    private String reservatedAt;
    @Schema(description = "예약금 유무(0:예약금없음 / 1:예약금있음)", example = "1")
    private Integer reservationFeeYn;
    @Schema(description = "결제수단id(1:현금 / 2:카드 / 3:계좌이체)", example = "1")
    private Integer paymentMethodId;
    @Schema(description = "예약금", example = "100000")
    private Integer amount;
}
