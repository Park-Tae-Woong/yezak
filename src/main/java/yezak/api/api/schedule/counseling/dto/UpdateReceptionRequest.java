package yezak.api.api.schedule.counseling.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(description = "예약 대기상태 업데이트")
public class UpdateReceptionRequest {
    @Schema(description = "방 id")
    private Long roomId;

    @Schema(description = "병원 내 플로우 진행상태 id")
    private Long statusId;

    @Schema(description = "환자 id")
    private Long patientId;

    @Schema(description = "접수사항 수정한 user id")
    private Long updatedUserId;
}
