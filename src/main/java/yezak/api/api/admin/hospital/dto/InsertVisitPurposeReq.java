package yezak.api.api.admin.hospital.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsertVisitPurposeReq {
    @Schema(description = "방문목적", example = "수납")
    private String name;
    @Schema(description = "목적 구분", example = "1")
    private int isCoverage;
    @Hidden
    @Schema(description = "병원 id", example = "1")
    private Long hospitalId;

}
