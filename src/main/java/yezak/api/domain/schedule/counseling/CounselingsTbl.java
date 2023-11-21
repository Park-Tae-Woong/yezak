package yezak.api.domain.schedule.counseling;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "상담내용")
public class CounselingsTbl {
    @Schema(description = "id")
    private Long id;

    @Schema(description = "환자 id")
    private Long patientsId;

    @Schema(description = "상담사 id")
    private Long userId;

    @Schema(description = "상담 시/수술")
    private String askedProduct;

    @Schema(description = "상담노트")
    private String content;

    @Schema(description = "생성일자")
    private Timestamp createdAt;

    @Schema(description = "수정일자")
    private Timestamp updatedAt;
}
