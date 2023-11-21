package yezak.api.api.admin.permission.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class UserManagementDto {
    @Schema(description = "유저 이름", example = "홍길동")
    private String uName;
    @Schema(description = "상세직군명", example = "간호사")
    private String koName;
    @Schema(description = "권한명", example = "일반")
    private String gName;
    @Schema(description = "계정상태(활성화 : 0, 비활성화 : 1, 삭제 : 2)", example = "활성화")
    private String accountStatus;
    @Schema(description = "유저id", example = "1", hidden = true)
    private Long id;
    @Schema(description = "이메일", example = "test@test.com", hidden = true)
    private String email;
}
