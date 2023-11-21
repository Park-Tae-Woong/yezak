package yezak.api.api.management_support.emergency_contact_list.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmergencyContactListRes {
    @Schema(description = "번호", example = "1")
    private int no;
    @Schema(description = "유저id", example = "1")
    private Long id;
    @Schema(description = "이름", example = "홍길동")
    private String name;
    @Schema(description = "직군카테고리id", example = "1")
    private Long roleCategoryId;
    @Schema(description = "직군카테고리", example = "의사")
    private String roleCategoryName;
    @Schema(description = "전화번호", example = "01011111123")
    private String phoneNumber;
    @Schema(description = "이메일", example = "test@test.com")
    private String email;
    @Schema(description = "고용형태 id", example = "1")
    private Long employmentTypeId;
    @Schema(description = "고용형태", example = "정규직")
    private String employmentType;
    @Schema(description = "재직상태", example = "재직")
    private String employmentStatus;
}
