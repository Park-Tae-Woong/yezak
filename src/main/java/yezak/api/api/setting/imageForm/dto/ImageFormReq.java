package yezak.api.api.setting.imageForm.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
public class ImageFormReq {
    @Schema(description = "서식id", example = "1", hidden = true)
    private Long id;
    @Schema(description = "병원 id", example = "1", hidden = true)
    private Long hospitalId;
    @Schema(description = "서식명", example = "사전설문지")
    private String name;
}
