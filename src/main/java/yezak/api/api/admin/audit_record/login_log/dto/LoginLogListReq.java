package yezak.api.api.admin.audit_record.login_log.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginLogListReq {
    private Long hospitalId;
    private String start;
    private String end;
    private Integer offset;
    private Integer pageSize;
}
