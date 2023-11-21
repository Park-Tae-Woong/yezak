package yezak.api.domain.schedule.counseling;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "대면상담 이미지 파일")
public class CounselingAttachmentsTbl {
    @Schema(description = "id")
    private Long id;

    @Schema(description = "대면상담 id")
    private Long counselingId;

    @Schema(description = "파일명")
    private String fileName;

    @Schema(description = "저장된 주소")
    private String filePath;

    @Schema(description = "확장자")
    private String fileExtension;

    @Schema(description = "크기")
    private String fileSize;

    @Schema(description = "생성일자")
    private Timestamp createdAt;

    @Schema(description = "수정일자")
    private Timestamp updatedAt;
}
