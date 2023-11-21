package yezak.api.api.user.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import yezak.api.util.mail.MailRequest;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class UserJoinReq {
    @Schema(description = "유저id", example = "1", hidden = true)
    private Long id;
    @Schema(description = "email", example = "twpark@m-ultiply.com")
    private String email;
    @Schema(description = "비밀번호", example = "123123123!", hidden = true)
    private String temporaryPassword;
    @Hidden
    @Schema(description = "병원id", example = "1")
    private Long hospitalId;
    private Long roleCategoryId;
    @Schema(description = "직군id", example = "1")
    private Long roleId;
    @Schema(description = "이름", example = "박태웅")
    private String name;
    @Schema(description = "메일", hidden = true)
    private MailRequest mailRequest;

}
