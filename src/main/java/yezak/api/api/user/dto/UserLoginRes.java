package yezak.api.api.user.dto;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class UserLoginRes {
    private String email;
    private Long roleId;
    private Long hospitalId;
}
