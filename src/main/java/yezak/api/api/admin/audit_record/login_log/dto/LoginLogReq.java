package yezak.api.api.admin.audit_record.login_log.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginLogReq {
    @Schema(description = "병원 id", example = "1", hidden = true)
    private long hospitalId;
    @Schema(description = "ip", example = "11.11.11.11", hidden = true)
    private String ip;
    @Schema(description = "로그기록", example = "test", hidden = true)
    private String message;
    @Schema(description = "변경을 한 유저", example = "1")
    private Long actUserId;
    @Schema(description = "변경 된 유저", example = "2")
    private Long affectedUserId;
    @Schema(description = "결과값", example = "실패")
    private String result;
    @Schema(description = "구분", example = "로그인")
    private String distinction;
}
