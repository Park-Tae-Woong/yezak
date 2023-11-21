package yezak.api.api.user.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NameAndRoleName {
    private String name;
    private String roleName;
}
