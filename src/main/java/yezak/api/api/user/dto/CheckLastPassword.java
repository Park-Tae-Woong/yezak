package yezak.api.api.user.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckLastPassword {
    private String lastPassword;
    private String lastLastPassword;
}
