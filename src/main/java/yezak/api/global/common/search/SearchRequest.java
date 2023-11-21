package yezak.api.global.common.search;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequest {
    @Schema(description = "검색내용")
    private String searchValue;

    @Schema(description = "병원 id")
    private Long hospitalId;

    @Schema(description = "환자 id")
    private Long patientId;

    @Schema(description = "진료기록 id")
    private Long mrId;
}
