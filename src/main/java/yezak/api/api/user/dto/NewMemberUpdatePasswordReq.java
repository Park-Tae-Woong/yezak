package yezak.api.api.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewMemberUpdatePasswordReq {
    @Schema(description = "유저 email", example = "test@test.com")
    private String email;
    @Schema(description = "비밀번호", example = "123123123!")
    private String password;
}
