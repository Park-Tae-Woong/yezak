package yezak.api.api.admin.hospital.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HospitalInfoRes {
    @Schema(description = "병원명", example = "문래요양병원")
    private String hospitalName;

    @Schema(description = "요양기관번호", example = "12345")
    private String hospitalNumber;

    @Schema(description = "사업자등록번호", example = "111-222-33333")
    private String businessRegistrationNumber;

    @Schema(description = "대표자명", example = "박태웅")
    private String userName;

    @Schema(description = "주민등록번호 앞자리", example = "920428")
    private String firstRegistrationNumber;

    @Schema(description = "주민등록번호 뒷자리", example = "1111111")
    private String secondRegistrationNumber;

    @Schema(description = "우편번호", example = "12345")
    private String zipCode;

    @Schema(description = "병원주소", example = "대구 중구 장관동")
    private String address;

    @Schema(description = "병원상세주소", example = "360-1")
    private String addressDetail;

    @Schema(description = "전화번호", example = "010-4284-5560")
    private String phoneNumber;
}
