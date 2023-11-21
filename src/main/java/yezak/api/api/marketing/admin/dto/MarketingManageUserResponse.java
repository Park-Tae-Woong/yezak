package yezak.api.api.marketing.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "담당자 조회 res")
public class MarketingManageUserResponse {
    @Schema(description = "담당자id", example = "1")
    private Long value; //private Long id;
    @Schema(description = "담당자명", example = "홍길동")
    private String text; //private String name;
}
