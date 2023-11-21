package yezak.api.api.schedule.counseling.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(description = "환자 조회용")
public class SearchPatientsRequest {
    @Schema(description = "검색내용(이름/차트번호/생년월일/전화번호)")
    private String searchValue;

    @Schema(description = "병원 id")
    private Long hospitalId;
}
