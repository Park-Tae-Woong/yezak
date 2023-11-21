package yezak.api.api.schedule.counseling.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "대기 리스트")
public class WaitingResponse {
    @Schema(description = "접수 id")
    private Long receptionId;

    @Schema(description = "차트번호")
    private String chartNumber;

    @Schema(description = "환자 id")
    private Long patientId;

    @Schema(description = "수진자명")
    private String name;

    @JsonFormat(pattern="HH:mm")
    @Schema(description = "예약시간")
    private String reservedAt;

    @Schema(description = "담당자")
    private String manager;

    @Schema(description = "방 이름")
    private String roomName;

    @Schema(description = "상태")
    private String status;
}
