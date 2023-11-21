package yezak.api.api.schedule.counseling.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "상담 차트이력 리스트")
public class CounselingHistoryResponse {
    @Schema(description = "상담 id")
    private Long id;

    @JsonFormat(pattern="yyyy.MM.dd HH:mm")
    @Schema(description = "상담일시")
    private String createdAt;

    @Schema(description = "직군명")
    private String jobName;

    @Schema(description = "담당자")
    private String manager;

    @Schema(description = "상담 시/수술")
    private String askedProduct;

    @Schema(description = "상담노트")
    private String content;
}
