package yezak.api.api.admin.system.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SystemMenuInfoResponse {
    @Schema(description = "메뉴명", example = "접수/수납")
    private String koName;
    @Schema(description = "id", example = "1")
    private Long id;
}
