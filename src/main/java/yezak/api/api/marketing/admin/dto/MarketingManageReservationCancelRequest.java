package yezak.api.api.marketing.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketingManageReservationCancelRequest {
    @Schema(description = "row id", example = "1")
    private Long id;
    @Schema(description = "예약상태id((1:- / 2:입금대기 / 3:입금 완료 / 4:예약 완료 / 5:예약취소 / 6:반환대기 / 7:반환완료))", example = "3")
    private Long reservationStatus;
    @Schema(description = "취소사유", example = "취소사유")
    private String cancelReason;
}
