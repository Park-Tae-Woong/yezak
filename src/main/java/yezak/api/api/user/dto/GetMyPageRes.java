package yezak.api.api.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetMyPageRes {
    @Schema(description = "이름", example = "홍길동")
    private String name;
    @Schema(description = "이메일", example = "test@test.com")
    private String email;
    @Schema(description = "주민번호 앞자리", example = "920428")
    private String firstRegistrationNumber;
    @Schema(description = "주민번호 뒷자리", example = "1111111")
    private String secondRegistrationNumber;
    @Schema(description = "성별", example = "1")
    private int sex;
    @Schema(description = "전화번호", example = "01012341234")
    private String phoneNumber;
    @Schema(description = "비상번호", example = "01043214321")
    private String subPhoneNumber;
    @Schema(description = "우편번호", example = "08302")
    private String postCode;
    @Schema(description = "주소", example = "대구 북구 태전동")
    private String address;
    @Schema(description = "상세주소", example = "230-1")
    private String addressDetail;
    @Schema(description = "면허번호", example = "110110110")
    private String licenseNumber;

}
