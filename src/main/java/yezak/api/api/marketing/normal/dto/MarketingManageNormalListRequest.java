package yezak.api.api.marketing.normal.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketingManageNormalListRequest {
    @Hidden
    @Schema(description = "병원id", example = "1")
    private Long hospitalId;
    @Schema(description = "담당자id", example = "1")
    private Long userId;
//    @Schema(description = "시작일", example = "2023-05-01")
//    private LocalDate startDate;
//    @Schema(description = "종료일", example = "2023-05-31")
//    private LocalDate endDate;
//    @Schema(description = "상담상태id", example = "1(1미연락 2상담진행중 3상담완료 4연락불가)")
//    private Long counselingStatusId;
//    @Schema(description = "예약상태id", example = "3(1:- / 2:입금대기 / 3:입금 완료 / 4:예약 완료 / 5:예약취소 / 6:반환대기 / 7:반환완료)")
//    private Long reservationStatusId;
    @Schema(description = "검색 이름or전화번호", example = "김고객 or 010-1234-5678")
    private String searchKeyword;
}
