package yezak.api.api.admin.audit_record.login_log.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginLogRes {
    @Schema(description = "계정", example = "test@test.com")
    private String email;
    @Schema(description = "구분", example = "로그인")
    private String distinction;
    @Schema(description = "결과", example = "성공")
    private String result;
    @Schema(description = "내용", example = "Test")
    private String message;
    @Schema(description = "생성날짜", example = "2022-02-02 09:00:00")
    private LocalDateTime createdAt;
    @Schema(description = "ip", example = "11.11.11.11")
    private String ip;


}
