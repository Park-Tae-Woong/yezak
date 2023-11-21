package yezak.api.api.schedule.dressing.dto;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsertDressingReq {
    @Hidden
    private Long id;
    private Long operationId;
    private String content;
}
