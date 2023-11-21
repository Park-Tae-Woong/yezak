package yezak.api.api.management_support.meeting.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeetingReq {
    @Schema(description = "회의 id", example = "1", hidden = true)
    private Long id;
    @Schema(description = "회의 제목", example = "2023 워크샵 장소")
    private String title;
    @Schema(description = "날짜", example = "2023-05-11")
    private String date;
    @Schema(description = "시작시간", example = "10:00:00")
    private String startTime;
    @Schema(description = "종료시간", example = "11:00:00")
    private String endTime;
    @Schema(description = "장소", example = "회의실3")
    private String location;
    @Schema(description = "주최자", example = "1")
    private Long userId;

    @Hidden
    @Schema(description = "병원id", example = "1")
    private Long hospitalId;

    @Hidden
    @Schema(description = "회의 id", example = "1")
    private Long meetingId;
//    @Schema(description = "참석자", example = "1, 2, 3")
    private List<Long> attendUserIds;
}
