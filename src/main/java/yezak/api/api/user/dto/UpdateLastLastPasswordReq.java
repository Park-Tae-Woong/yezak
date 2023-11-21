package yezak.api.api.user.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateLastLastPasswordReq {
    private String email;
    private String lastLastPassword;
}
