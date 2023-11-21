package yezak.api.api.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindPasswordReq {
    @Schema(description = "이메일", example = "test@test.com")
    private String email;
    @Schema(description = "비밀번호", example = "random", hidden = true)
    private String password;

}
