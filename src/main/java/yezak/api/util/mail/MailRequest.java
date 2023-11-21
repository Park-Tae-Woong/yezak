package yezak.api.util.mail;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class MailRequest {
    @Schema(description = "받는 사람의 이메일", example = "twpark@m-ultiply.com")
    private String toEmail;
    @Schema(description = "제목", example = "회원가입 초대 메일")
    private String subject;
    @Schema(description = "내용", example = "https://docs.m-ultiply.com/auth")
    private String message;
}
