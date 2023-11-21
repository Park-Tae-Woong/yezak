package yezak.api.api.admin.permission.auth.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsertToShow {
    private Long roleId;
    private Long depth3NavigationId;
}
