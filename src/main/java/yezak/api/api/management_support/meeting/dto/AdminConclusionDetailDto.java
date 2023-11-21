package yezak.api.api.management_support.meeting.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminConclusionDetailDto {
    @Schema(description = "회의명", example = "세미나")
    private String title;
    @Schema(description = "할일", example = "워크샵 기획")
    private String content;
    @Schema(description = "마감일자", example = "2022.12.20")
    private String dueDate;
    @Schema(description = "마감시간", example = "15:00")
    private String dueTime;
    @Schema(description = "상세내용", example = "test")
    private String detail;
    @Schema(description = "담당자", example = "윤영빈")
    private String manager;

    private String evaluation;

    private List<ConclusionFileListRes> conclusionFileListResList;

}
