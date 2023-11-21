package yezak.api.api.management_support.meeting.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeetingListReq {
    @Schema(description = "시작날짜", example = "2023-05-01")
    private String startDate; //조회 조건
    @Schema(description = "마지막날짜", example = "2023-05-07")
    private String endDate; //조회 조건
    @Schema(description = "검색어", example = "제목 및 본문")
    private String searchValue; //조회 조건
    @Hidden
    private Long hospitalId;
    private Integer offset;
    private Integer pageSize;
}
