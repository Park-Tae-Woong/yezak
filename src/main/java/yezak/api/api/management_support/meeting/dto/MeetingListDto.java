package yezak.api.api.management_support.meeting.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeetingListDto {
    private Long id;
    @Schema(description = "회의 일시", example = "2022-02-02 (화)")
    private String date;
    @Schema(description = "진행 시간", example = "10:00 - 11:0")
    private String time;
    @Schema(description = "장소", example = "회의실")
    private String location;
    @Schema(description = "제목", example = "2023 워크샵 장소")
    private String title;
    @Schema(description = "회의 주최자", example = "윤영빈")
    private String createUser;
    @Schema(description = "회의 참석자", example = "윤영빈 외 5명")
    private String attendUser;
    @Schema(description = "진행현황", example = "2/4")
    private String status;
}
