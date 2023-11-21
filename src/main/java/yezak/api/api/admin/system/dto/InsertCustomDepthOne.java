package yezak.api.api.admin.system.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsertCustomDepthOne {
    private Long depth1NavigationId;
    private Long hospitalId;
    private String koName;
}
