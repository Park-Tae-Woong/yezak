package yezak.api.api.user.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class UserUpdateReq {
    @Hidden
    @Schema(description = "유저 id", example = "1")
    private Long id;
    @Schema(description = "이름", example = "홍길동")
    private String name;
    @Schema(description = "주민등록번호 앞자리", example = "920428")
    private String firstRegistrationNumber;
    @Schema(description = "주민등록번호 뒷자리", example = "1111111")
    private String secondRegistrationNumber;
    @Schema(description = "성별", example = "1")
    private int sex;
    @Schema(description = "연락처", example = "01012341234")
    private String phoneNumber;
    @Schema(description = "비상 연락처", example = "01043214321")
    private String subPhoneNumber;
    @Schema(description = "우편번호", example = "08302")
    private String postCode;
    @Schema(description = "주소", example = "대구시 북구 국우동")
    private String address;
    @Schema(description = "상세주소", example = "101동 1003호")
    private String addressDetail;
    @Schema(description = "의사번호 or 의료번호", example = "123123123")
    private String licenseNumber;
}
