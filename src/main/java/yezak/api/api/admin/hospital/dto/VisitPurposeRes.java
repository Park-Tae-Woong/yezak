package yezak.api.api.admin.hospital.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitPurposeRes {
    @Schema(description = "id", example = "1")
    private int id;
    @Schema(description = "방문목적", example = "수납")
    private String name;
    @Schema(description = "목적 구분(0:구분없음 / 1:급여 / 2:비급여)", example = "1")
    private int isCoverage;
}
