package yezak.api.domain;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
@ToString
@NoArgsConstructor
public class User {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Long hospitalId;
    private Long roleId;
    private LocalDateTime passwordChangedAt;
    private String temporaryPassword;
    private LocalDateTime temporaryPasswordExpiredAt;
    private String lastPassword;
    private String lastLastPassword;
    private Long isStopped;
    private Long isLocked;
    private Long isDeleted;


}
