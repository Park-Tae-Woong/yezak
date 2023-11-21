package yezak.api.api.reception.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
public class NewPatientReq {
    @Schema(description = "이름", example = "박태웅")
    private String name;
    @Schema(description = "주민번호앞자리", example = "920428")
    private String firstRegistrationNumber;
    @Schema(description = "주민번호뒷자리", example = "1111111")
    private String secondRegistrationNumber;
    @Schema(description = "전화번호", example = "01042845560")
    private String phoneNumber;
    @Schema(description = "주소", example = "대구 북구 국우동")
    private String address;
    @Schema(description = "상새주소", example = "행복주택 202호")
    private String addressDetail;
    @Schema(description = "개인정보 수집/이용 동의", example = "1")
    private int agreedPersonal;
    @Schema(description = "(선택)마케팅 정보 수신 동의", example = "1")
    private int agreedMarketing;
    @Schema(description = "(선택)연말정산 자료제출 동의", example = "0")
    private int agreedSettlement;
    @Hidden
    private Long sexId;
    @Hidden
    private Long hospitalId;
}
