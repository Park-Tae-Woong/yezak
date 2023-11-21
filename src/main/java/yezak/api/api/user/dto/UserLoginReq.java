package yezak.api.api.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class UserLoginReq {
    @Schema(description = "이메일", example = "test@test.com")
    private String email;
    @Schema(description = "비밀번호", example = "123123123!")
    private String password;

    @Schema(hidden = true)
    public UsernamePasswordAuthenticationToken toAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }


}
