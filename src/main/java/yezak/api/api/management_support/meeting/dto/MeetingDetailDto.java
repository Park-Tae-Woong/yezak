package yezak.api.api.management_support.meeting.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeetingDetailDto {
    @Schema(description = "제목", example = "3분기 CPA업체 선정")
    private String title;
    @Schema(description = "회의날짜", example = "2023.05.05(수)")
    private String date;
    @Schema(description = "회의장소", example = "회의실")
    private String location;
    @Schema(description = "회의주최자", example = "윤영빈")
    private String createUser;
    @Schema(description = "회의참가자", example = "윤영빈, 윤영빈, 윤영빈")
    private String attendUser;
    @Schema(description = "회의내용", example = "내용")
    private String meetingContent;
    private List<ConclusionListRes> conclusionListRes;

}
