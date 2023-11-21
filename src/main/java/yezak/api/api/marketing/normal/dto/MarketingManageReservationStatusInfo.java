package yezak.api.api.marketing.normal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketingManageReservationStatusInfo {
    @Schema(description = "예약상태id", example = "3(1:- / 2:입금대기 / 3:입금 완료 / 4:예약 완료 / 5:예약취소 / 6:반환대기 / 7:반환완료)")
    private Long value; //id;
    @Schema(description = "예약상태명", example = "입금완료")
    private String text; //name;
}
