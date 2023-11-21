package yezak.api.api.schedule.counseling.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Schema(description = "일정 예약")
public class ReservationRequest {
    @Schema(description = "환자 id")
    private Long patientId;

    @Schema(description = "방 id")
    private Long roomId;

    @Schema(description = "예약일시")
    private Timestamp reservatedAt;
}
