package yezak.api.api.admin.permission.auth.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthLogReq {
    private Long affectedUserId;
    private Long actUserId;
    private Long roleId;
    private String depth3NavigationsArray;
    private String ip;
    private Long hospitalId;
}
