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
public class MarketingManageRegistPatientInfoRequest {
    @Hidden
    @Schema(description = "고객id", example = "1")
    private Long id;
    @Hidden
    @Schema(description = "병원id", example = "1")
    private Long hospitalId;
    @Schema(description = "이름", example = "홍길동")
    private String name;
    @Schema(description = "생년월일", example = "19890827")
    private String birth;
    @Schema(description = "전화번호", example = "010-1234-5678")
    private String phoneNumber;
    @Schema(description = "성별(1:남성 / 2:여성 / 3:기타)", example = "1")
    private Integer genderId;
    @Schema(description = "혼인유무(1:기혼 / 2:미혼)", example = "1")
    private Integer marriedId;
    @Schema(description = "지역", example = "경기도 안산시")
    private String address;
    @Schema(description = "상담가능 시간", example = "11:30")
    private String availableTime;
    @Schema(description = "유입경로id", example = "1")
    private Long accessRootId;
    @Hidden
    private Long patientCounselingId;
    @Hidden
    private Integer secondRegistrationNumber;
}
