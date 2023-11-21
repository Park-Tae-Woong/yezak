package yezak.api.api.admin.permission.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import yezak.api.api.user.dto.GetChangeAmount;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class UserManageDetailDto {
    @Schema(description = "유저 id", example = "1")
    private Long userId;
    @Schema(description = "유저 이름", example = "홍길동")
    private String name;
    @Schema(description = "직군 id", example = "1")
    private Long roleId;
    @Schema(description = "직군명", example = "경영지원")
    private String koName;
    @Schema(description = "이메일", example = "test@test.com")
    private String email;
    @Schema(description = "성별", example = "1")
    private int sex;
    @Schema(description = "주민번호 앞자리", example = "920428")
    private String firstRegistrationNumber;
    @Schema(description = "주민번호 뒷자리", example = "1111111")
    private String secondRegistrationNumber;
    @Schema(description = "연락처", example = "01011111234")
    private String phoneNumber;
    @Schema(description = "비상연락처", example = "01043214321")
    private String subPhoneNumber;
    @Schema(description = "주소", example = "서울시 구로구 정기동")
    private String address;
    @Schema(description = "상세주소", example = "101동 102호")
    private String addressDetail;
    @Schema(description = "급여형태 id", example = "1")
    private Long salaryTypeId;
    @Schema(description = "급여형태", example = "연봉")
    private String stName;
    @Schema(description = "급여시작", example = "1")
    private String salaryInfoStart;
    @Schema(description = "급여종료", example = "1")
    private String salaryInfoEnd;
    @Schema(description = "변동내역", example = "변동내역")
    private LocalDateTime createdAt;
    @Schema(description = "변동 후 금액", example = "40000000")
    private Long amount;
    @Schema(description = "변동 전 금액", example = "30000000")
    private Long agoAmount; //임의
    @Schema(description = "진료과 id", example = "1")
    private Long treatmentSubjectId;
    @Schema(description = "진료과", example = "비뇨의학과")
    private String tsName;
    @Schema(description = "고용형태 id", example = "1")
    private Long employmentTypeId;
    @Schema(description = "고용형태", example = "정규직")
    private String etName;
    @Schema(description = "직군대분류 id", example = "1")
    private Long roleCategoryId;
    @Schema(description = "직군카테고리", example = "비뇨의학과")
    private String rcKoName;
    @Schema(description = "입사일", example = "2002년 11월 17일")
    private String joinedAt;
    @Schema(description = "수습기간 여부", example = "1")
    private int internshipYn;
    @Schema(description = "수습기간 시작날짜", example = "2002. 11. 17.")
    private String internshipStart;
    @Schema(description = "수습기간 종료날짜", example = "2002. 11. 18.")
    private String internshipEnd;
    @Schema(description = "재직구분 id", example = "1")
    private Long employmentStatusId;
    @Schema(description = "재직구분", example = "재직")
    private String esName;
    @Schema(description = "휴직 시작날짜", example = "2002. 11. 17.")
    private String restStart;
    @Schema(description = "휴직 종료 날짜", example = "2002. 11. 17.")
    private String restEnd;
    @Schema(description = "퇴직연금 가입일", example = "2002. 11. 18.")
    private LocalDateTime pensionDate;
    @Schema(description = "퇴직일", example = "2002. 11. 18.")
    private LocalDateTime leavedAt;
    @Schema(description = "퇴직사유", example = "대뇌의 전두엽이 떨린 이유")
    private String leaveReason;

    private List<GetChangeAmount> getChangeAmount;
}
