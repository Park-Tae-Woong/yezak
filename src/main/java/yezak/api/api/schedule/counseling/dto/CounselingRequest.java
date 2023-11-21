package yezak.api.api.schedule.counseling.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(description = "상담 insert")
public class CounselingRequest {
    @Schema(description = "환자 id")
    private Long patientId;

    @Schema(description = "상담 시/수술")
    private String askedProduct;

    @Schema(description = "상담노트")
    private String content;

    @Schema(description = "예약할 방 id")
    private Long reservedRoomId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Schema(description = "예약일시")
    private String reservatedAt;

    @Schema(description = "보내기 할 방 id")
    private Long sendRoomId;

    @Schema(description = "상태 id")
    private Long statusId;
}
