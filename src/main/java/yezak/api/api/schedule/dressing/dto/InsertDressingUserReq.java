package yezak.api.api.schedule.dressing.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsertDressingUserReq {
    @Hidden
    private Long dressingId;
    private Long userId;
}
