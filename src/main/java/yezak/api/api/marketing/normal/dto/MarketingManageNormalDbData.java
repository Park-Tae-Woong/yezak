package yezak.api.api.marketing.normal.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketingManageNormalDbData {
    @Schema(description = "row id", example = "1")
    private Integer id;
    @Schema(description = "고객명", example = "김고객")
    private String name;
    @Schema(description = "전화번호", example = "010-1234-5678")
    private String phoneNumber;
    @Schema(description = "상담가능시간", example = "상관없음")
    private String availableTime;
    @Schema(description = "유입경로명", example = "네이버 블로그 1번광고")
    private String accessRootName;
    @Schema(description = "등록일", example = "2023-05-01")
    private LocalDate createdAt;
    @Schema(description = "담당자id", example = "1")
    private Long chargeId;
    @Schema(description = "담당자명", example = "김상담")
    private String chargeName;
    @Schema(description = "상담상태", example = "상담완료")
    private String counselingStatus;
    @Schema(description = "상담상태id", example = "1")
    private Integer counselingStatusId;
    @Schema(description = "예약상태", example = "입금완료")
    private String reservationStatus;
    @Schema(description = "예약상태id", example = "1")
    private Integer reservationStatusId;
    @Schema(description = "상담이력")
    private List<MarketingManageNormalCounselHistoryInfo> counselHistoryInfo;
}
