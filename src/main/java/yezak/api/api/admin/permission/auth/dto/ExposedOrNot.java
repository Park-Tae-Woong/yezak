package yezak.api.api.admin.permission.auth.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExposedOrNot {
    private Long roleId;
    private List<Long> depth3NavigationIds;
}
