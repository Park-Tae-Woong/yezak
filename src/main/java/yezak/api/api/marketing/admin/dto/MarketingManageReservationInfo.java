package yezak.api.api.marketing.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketingManageReservationInfo {
    @Schema(description = "예약정보id", example = "1")
    private Long id;
    @Schema(description = "대기실id", example = "1")
    private Long roomId;
    @Schema(description = "대기실명", example = "대면상담실1")
    private String roomName;
    @Schema(description = "예약일자", example = "2023-05-01 13:30:00")
    private LocalDateTime reservationDate;
    @Schema(description = "예약상태id", example = "3(1:- / 2:입금대기 / 3:입금 완료 / 4:예약 완료 / 5:예약취소 / 6:반환대기 / 7:반환완료)")
    private Long reservationStatusId;
    @Schema(description = "예약상태명", example = "입금완료")
    private String reservationStatusName;
    @Schema(description = "예약금 유무", example = "Y")
    private String reservationFeeYn;
    @Schema(description = "결제수단id", example = "3(1:현금 / 2:카드 / 3:계좌이체)")
    private Integer paymentId;
    @Schema(description = "결제수단명", example = "계좌이체")
    private String paymentMethodName;
    @Schema(description = "예약금", example = "100000")
    private Integer amount;
    @Schema(description = "예약 취소 사유", example = "예약 취소 사유")
    private String cancelReason;
}
