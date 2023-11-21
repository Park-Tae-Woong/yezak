package yezak.api.api.user.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateLastPasswordReq {
    private String lastPassword;
    private String email;
}
