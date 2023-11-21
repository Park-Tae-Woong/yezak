package yezak.api.api.schedule.counseling.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "환자 이름/가족력")
public class NameInheritFactor {
    @Schema(description = "이름")
    private String name;

    @Schema(description = "가족력")
    private String inheritFactor;
}
