package yezak.api.api.marketing.admin.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccessRoot {
    @Schema(description = "유입경로id", example = "1")
    private Long value; //id;
    @Schema(description = "유입경로명", example = "네이버 블로그1")
    private String text; //name;
}
