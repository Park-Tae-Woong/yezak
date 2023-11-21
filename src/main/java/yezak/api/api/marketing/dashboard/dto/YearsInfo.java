package yezak.api.api.marketing.dashboard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class YearsInfo {
    @Schema(description = "value", example = "2023")
    private Integer value;
    @Schema(description = "text", example = "2023년")
    private String text;
}
