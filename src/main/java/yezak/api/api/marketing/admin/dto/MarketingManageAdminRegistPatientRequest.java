package yezak.api.api.marketing.admin.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MarketingManageAdminRegistPatientRequest {
    @Hidden
    @Schema(description = "고객id", example = "1")
    private Long patientId = null;
    @Hidden
    @Schema(description = "예약id", example = "1")
    private Long reservationId;
    @Schema(description = "상담상태id(1미연락 2상담진행중 3상담완료 4연락불가)", example = "1")
    private Long counselState;
    @Schema(description = "이름", example = "홍길동")
    private String name;
    @Schema(description = "생년월일", example = "19890827")
    private String birth;
    @Schema(description = "전화번호", example = "010-1234-5678")
    private String phoneNumber;
    @Schema(description = "성별", example = "1(1:남성 / 2:여성)")
    private Integer gender;
    @Schema(description = "혼인유무", example = "1(1:기혼 / 2:미혼)")
    private Integer married;
    @Schema(description = "지역", example = "경기도 안산시")
    private String address;
    @Schema(description = "상담가능 시간", example = "11:30")
    private String availableTime;
    @Schema(description = "유입경로id", example = "1")
    private Long accessRoot;
    @Schema(description = "문의사항", example = "문의사항")
    private String inquiry;
    @Schema(description = "담당자id", example = "1")
    private Integer chargeId;
    @Schema(description = "직군id", example = "1")
    private Integer chargeRoleId;
    @Schema(description = "상담 시/수술", example = "시술1,시술2,시술3")
    private String askedProductStr;
    @Schema(description = "상담내용", example = "상담내용")
    private String counselContent;
    @Hidden
    @Schema(description = "병원id", example = "1")
    private Long hospitalId;
    @Schema(description = "대기실id", example = "1")
    private Long roomId;
    @Schema(description = "예약일시", example = "2023-05-01 13:30:00")
    private LocalDateTime reservatedAt;
    @Schema(description = "예약금 유무(0:예약금없음 / 1:예약금있음)", example = "1")
    private Integer reservationFeeYn;
    @Schema(description = "결제수단id(1:현금 / 2:카드 / 3:계좌이체)", example = "1")
    private Integer paymentMethodId;
    @Schema(description = "예약금", example = "100000")
    private Integer amount;
}
