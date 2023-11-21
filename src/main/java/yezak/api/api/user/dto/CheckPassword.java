package yezak.api.api.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckPassword {
    @Schema(description = "유저id", example = "1", hidden = true)
    private Long userId;
    @Schema(description = "비밀번호", example = "123123123!")
    private String password;
}
