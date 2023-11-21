package yezak.api.api.reception.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UpdatePatientInfoReq {
    @Schema(description = "이름", example = "박태웅")
    private String name;
    @Schema(description = "주민번호 앞자리", example = "920428")
    private String firstRegistrationNumber;
    @Schema(description = "주민번호 뒷자리", example = "1111111")
    private String secondRegistrationNumber;
    @Schema(description = "전화번호", example = "01042845560")
    private String phoneNumber;
    @Schema(description = "주소", example = "대구 중구")
    private String address;
    @Schema(description = "상세주소", example = "301호")
    private String addressDetail;
    @Schema(description = "(필수)개인정보 수집/이용 동의", example = "1")
    private int agreedPersonal;
    @Schema(description = "(선택)마케팅 정보 수신 동의", example = "1")
    private int agreedMarketing;
    @Schema(description = "(선택)연말정산 자료제출 동의", example = "1")
    private int agreedSettlement;
    @Schema(description = "환자 id", example = "37")
    private Long patientId;
}
